/*
 * Decompiled with CFR 0_110.
 */
package com.squareup.okhttp.internal;

import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

public final class DiskLruCache
implements Closeable {
    static final long ANY_SEQUENCE_NUMBER = -1;
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    static final String JOURNAL_FILE_TEMP = "journal.tmp";
    static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,64}");
    static final String MAGIC = "libcore.io.DiskLruCache";
    private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream(){

        @Override
        public void write(int n2) throws IOException {
        }
    };
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    static final String VERSION_1 = "1";
    private final int appVersion;
    private final Runnable cleanupRunnable;
    private final File directory;
    final ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), Util.threadFactory("OkHttp DiskLruCache", true));
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    private BufferedSink journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap(0, 0.75f, true);
    private long maxSize;
    private long nextSequenceNumber = 0;
    private int redundantOpCount;
    private long size = 0;
    private final int valueCount;

    private DiskLruCache(File file, int n2, int n3, long l2) {
        this.cleanupRunnable = new Runnable(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void run() {
                DiskLruCache diskLruCache = DiskLruCache.this;
                synchronized (diskLruCache) {
                    if (DiskLruCache.this.journalWriter == null) {
                        return;
                    }
                    try {
                        DiskLruCache.this.trimToSize();
                        if (DiskLruCache.this.journalRebuildRequired()) {
                            DiskLruCache.this.rebuildJournal();
                            DiskLruCache.this.redundantOpCount = 0;
                        }
                        return;
                    }
                    catch (IOException var2_2) {
                        throw new RuntimeException(var2_2);
                    }
                }
            }
        };
        this.directory = file;
        this.appVersion = n2;
        this.journalFile = new File(file, "journal");
        this.journalFileTmp = new File(file, "journal.tmp");
        this.journalFileBackup = new File(file, "journal.bkp");
        this.valueCount = n3;
        this.maxSize = l2;
    }

    private void checkNotClosed() {
        if (this.journalWriter == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private void completeEdit(Editor var1_1, boolean var2_2) throws IOException {
        // MONITORENTER : this
        var8_3 = Editor.access$1400((Editor)var1_1);
        if (Entry.access$700(var8_3) != var1_1) {
            throw new IllegalStateException();
        }
        if (var2_2 && !Entry.access$600(var8_3)) {
            for (var3_4 = 0; var3_4 < this.valueCount; ++var3_4) {
                if (!Editor.access$1500((Editor)var1_1)[var3_4]) {
                    var1_1.abort();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + var3_4);
                }
                if (var8_3.getDirtyFile(var3_4).exists()) continue;
                var1_1.abort();
                return;
            }
        }
        var3_4 = 0;
        ** GOTO lbl18
        do {
            // MONITOREXIT : this
            return;
            break;
        } while (true);
lbl18: // 1 sources:
        do {
            if (var3_4 < this.valueCount) {
                var1_1 = var8_3.getDirtyFile(var3_4);
                if (var2_2) {
                    if (var1_1.exists()) {
                        var9_7 = var8_3.getCleanFile(var3_4);
                        var1_1.renameTo(var9_7);
                        var4_5 = Entry.access$1000(var8_3)[var3_4];
                        Entry.access$1000((Entry)var8_3)[var3_4] = var6_6 = var9_7.length();
                        this.size = this.size - var4_5 + var6_6;
                    }
                } else {
                    DiskLruCache.deleteIfExists((File)var1_1);
                }
            } else {
                ++this.redundantOpCount;
                Entry.access$702(var8_3, null);
                if (Entry.access$600(var8_3) | var2_2) {
                    Entry.access$602(var8_3, true);
                    this.journalWriter.writeUtf8("CLEAN").writeByte(32);
                    this.journalWriter.writeUtf8(Entry.access$1100(var8_3));
                    this.journalWriter.writeUtf8(var8_3.getLengths());
                    this.journalWriter.writeByte(10);
                    if (var2_2) {
                        var4_5 = this.nextSequenceNumber;
                        this.nextSequenceNumber = 1 + var4_5;
                        Entry.access$1202(var8_3, var4_5);
                    }
                } else {
                    this.lruEntries.remove(Entry.access$1100(var8_3));
                    this.journalWriter.writeUtf8("REMOVE").writeByte(32);
                    this.journalWriter.writeUtf8(Entry.access$1100(var8_3));
                    this.journalWriter.writeByte(10);
                }
                this.journalWriter.flush();
                if (this.size <= this.maxSize && !this.journalRebuildRequired()) ** continue;
                this.executorService.execute(this.cleanupRunnable);
                return;
            }
            ++var3_4;
        } while (true);
    }

    private static void deleteIfExists(File file) throws IOException {
        if (!file.delete() && file.exists()) {
            throw new IOException("failed to delete " + file);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private Editor edit(String string2, long l2) throws IOException {
        Object object = null;
        synchronized (this) {
            Object object2;
            this.checkNotClosed();
            this.validateKey(string2);
            Entry entry = this.lruEntries.get(string2);
            if (l2 != -1) {
                object2 = object;
                if (entry == null) return object2;
                long l3 = entry.sequenceNumber;
                if (l3 != l2) {
                    return object;
                }
            }
            if (entry == null) {
                object2 = new Entry(string2);
                this.lruEntries.put(string2, (Entry)object2);
            } else {
                Editor editor = entry.currentEditor;
                object2 = entry;
                if (editor != null) {
                    return object;
                }
            }
            object = new Editor((Entry)object2);
            ((Entry)object2).currentEditor = (Editor)object;
            this.journalWriter.writeUtf8("DIRTY").writeByte(32).writeUtf8(string2).writeByte(10);
            this.journalWriter.flush();
            return object;
        }
    }

    private static String inputStreamToString(InputStream inputStream) throws IOException {
        try {
            Object object = new Buffer();
            object.writeAll(Okio.source(inputStream));
            object = object.readUtf8();
            return object;
        }
        finally {
            Util.closeQuietly(inputStream);
        }
    }

    private boolean journalRebuildRequired() {
        if (this.redundantOpCount >= 2000 && this.redundantOpCount >= this.lruEntries.size()) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static DiskLruCache open(File object, int n2, int n3, long l2) throws IOException {
        if (l2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (n3 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        Object object2 = new File((File)object, "journal.bkp");
        if (object2.exists()) {
            File file = new File((File)object, "journal");
            if (file.exists()) {
                object2.delete();
            } else {
                DiskLruCache.renameTo((File)object2, file, false);
            }
        }
        object2 = new DiskLruCache((File)object, n2, n3, l2);
        if (object2.journalFile.exists()) {
            try {
                object2.readJournal();
                object2.processJournal();
                object2.journalWriter = Okio.buffer(Okio.sink(new FileOutputStream(object2.journalFile, true)));
                return object2;
            }
            catch (IOException var6_6) {
                Platform.get().logW("DiskLruCache " + object + " is corrupt: " + var6_6.getMessage() + ", removing");
                object2.delete();
            }
        }
        object.mkdirs();
        object = new DiskLruCache((File)object, n2, n3, l2);
        object.rebuildJournal();
        return object;
    }

    private void processJournal() throws IOException {
        DiskLruCache.deleteIfExists(this.journalFileTmp);
        Iterator<Entry> iterator = this.lruEntries.values().iterator();
        while (iterator.hasNext()) {
            int n2;
            Entry entry = iterator.next();
            if (entry.currentEditor == null) {
                for (n2 = 0; n2 < this.valueCount; ++n2) {
                    this.size += entry.lengths[n2];
                }
                continue;
            }
            entry.currentEditor = null;
            for (n2 = 0; n2 < this.valueCount; ++n2) {
                DiskLruCache.deleteIfExists(entry.getCleanFile(n2));
                DiskLruCache.deleteIfExists(entry.getDirtyFile(n2));
            }
            iterator.remove();
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void readJournal() throws IOException {
        var2_1 = Okio.buffer(Okio.source(this.journalFile));
        var3_2 = var2_1.readUtf8LineStrict();
        var4_5 = var2_1.readUtf8LineStrict();
        var5_6 = var2_1.readUtf8LineStrict();
        var6_7 = var2_1.readUtf8LineStrict();
        var7_8 = var2_1.readUtf8LineStrict();
        if ("libcore.io.DiskLruCache".equals(var3_2) == false) throw new IOException("unexpected journal header: [" + var3_2 + ", " + var4_5 + ", " + var6_7 + ", " + var7_8 + "]");
        if ("1".equals(var4_5) == false) throw new IOException("unexpected journal header: [" + var3_2 + ", " + var4_5 + ", " + var6_7 + ", " + var7_8 + "]");
        if (Integer.toString(this.appVersion).equals(var5_6) == false) throw new IOException("unexpected journal header: [" + var3_2 + ", " + var4_5 + ", " + var6_7 + ", " + var7_8 + "]");
        if (Integer.toString(this.valueCount).equals(var6_7) == false) throw new IOException("unexpected journal header: [" + var3_2 + ", " + var4_5 + ", " + var6_7 + ", " + var7_8 + "]");
        if (!"".equals(var7_8)) {
            throw new IOException("unexpected journal header: [" + var3_2 + ", " + var4_5 + ", " + var6_7 + ", " + var7_8 + "]");
        }
        ** GOTO lbl18
        finally {
            Util.closeQuietly(var2_1);
        }
lbl18: // 1 sources:
        var1_9 = 0;
        do {
            this.readJournalLine(var2_1.readUtf8LineStrict());
            ++var1_9;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void readJournalLine(String arrstring) throws IOException {
        Entry entry;
        Object object;
        Object object2;
        int n2 = arrstring.indexOf(32);
        if (n2 == -1) {
            throw new IOException("unexpected journal line: " + (String)arrstring);
        }
        int n3 = n2 + 1;
        int n4 = arrstring.indexOf(32, n3);
        if (n4 == -1) {
            object2 = object = arrstring.substring(n3);
            if (n2 == "REMOVE".length()) {
                object2 = object;
                if (arrstring.startsWith("REMOVE")) {
                    this.lruEntries.remove(object);
                    return;
                }
            }
        } else {
            object2 = arrstring.substring(n3, n4);
        }
        object = entry = this.lruEntries.get(object2);
        if (entry == null) {
            object = new Entry((String)object2);
            this.lruEntries.put((String)object2, (Entry)object);
        }
        if (n4 != -1 && n2 == "CLEAN".length() && arrstring.startsWith("CLEAN")) {
            arrstring = arrstring.substring(n4 + 1).split(" ");
            ((Entry)object).readable = true;
            ((Entry)object).currentEditor = null;
            ((Entry)object).setLengths(arrstring);
            return;
        }
        if (n4 == -1 && n2 == "DIRTY".length() && arrstring.startsWith("DIRTY")) {
            ((Entry)object).currentEditor = new Editor((Entry)object);
            return;
        }
        if (n4 == -1 && n2 == "READ".length() && arrstring.startsWith("READ")) return;
        {
            throw new IOException("unexpected journal line: " + (String)arrstring);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void rebuildJournal() throws IOException {
        synchronized (this) {
            if (this.journalWriter != null) {
                this.journalWriter.close();
            }
            BufferedSink bufferedSink = Okio.buffer(Okio.sink(new FileOutputStream(this.journalFileTmp)));
            try {
                bufferedSink.writeUtf8("libcore.io.DiskLruCache").writeByte(10);
                bufferedSink.writeUtf8("1").writeByte(10);
                bufferedSink.writeUtf8(Integer.toString(this.appVersion)).writeByte(10);
                bufferedSink.writeUtf8(Integer.toString(this.valueCount)).writeByte(10);
                bufferedSink.writeByte(10);
                for (Entry entry : this.lruEntries.values()) {
                    if (entry.currentEditor != null) {
                        bufferedSink.writeUtf8("DIRTY").writeByte(32);
                        bufferedSink.writeUtf8(entry.key);
                        bufferedSink.writeByte(10);
                        continue;
                    }
                    bufferedSink.writeUtf8("CLEAN").writeByte(32);
                    bufferedSink.writeUtf8(entry.key);
                    bufferedSink.writeUtf8(entry.getLengths());
                    bufferedSink.writeByte(10);
                }
            }
            finally {
                bufferedSink.close();
            }
            if (this.journalFile.exists()) {
                DiskLruCache.renameTo(this.journalFile, this.journalFileBackup, true);
            }
            DiskLruCache.renameTo(this.journalFileTmp, this.journalFile, false);
            this.journalFileBackup.delete();
            this.journalWriter = Okio.buffer(Okio.sink(new FileOutputStream(this.journalFile, true)));
            return;
        }
    }

    private static void renameTo(File file, File file2, boolean bl2) throws IOException {
        if (bl2) {
            DiskLruCache.deleteIfExists(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    private void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            this.remove(this.lruEntries.entrySet().iterator().next().getKey());
        }
    }

    private void validateKey(String string2) {
        if (!LEGAL_KEY_PATTERN.matcher(string2).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + string2 + "\"");
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void close() throws IOException {
        synchronized (this) {
            Object[] arrobject = this.journalWriter;
            if (arrobject != null) {
                arrobject = this.lruEntries.values().toArray();
                int n2 = arrobject.length;
                int n3 = 0;
                do {
                    if (n3 < n2) {
                        Entry entry = (Entry)arrobject[n3];
                        if (entry.currentEditor != null) {
                            entry.currentEditor.abort();
                        }
                    } else {
                        this.trimToSize();
                        this.journalWriter.close();
                        this.journalWriter = null;
                        break;
                    }
                    ++n3;
                } while (true);
            }
            return;
        }
    }

    public void delete() throws IOException {
        this.close();
        Util.deleteContents(this.directory);
    }

    public Editor edit(String string2) throws IOException {
        return this.edit(string2, -1);
    }

    public void flush() throws IOException {
        synchronized (this) {
            this.checkNotClosed();
            this.trimToSize();
            this.journalWriter.flush();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Snapshot get(String string2) throws IOException {
        Snapshot snapshot = null;
        synchronized (this) {
            int n2;
            this.checkNotClosed();
            this.validateKey(string2);
            Entry entry = this.lruEntries.get(string2);
            if (entry == null) {
                return snapshot;
            }
            Snapshot snapshot2 = snapshot;
            if (!entry.readable) return snapshot2;
            InputStream[] arrinputStream = new InputStream[this.valueCount];
            try {
                for (n2 = 0; n2 < this.valueCount; ++n2) {
                    arrinputStream[n2] = new FileInputStream(entry.getCleanFile(n2));
                }
            }
            catch (FileNotFoundException var1_2) {
                n2 = 0;
                do {
                    snapshot2 = snapshot;
                    if (n2 >= this.valueCount) return snapshot2;
                    snapshot2 = snapshot;
                    if (arrinputStream[n2] == null) return snapshot2;
                    Util.closeQuietly(arrinputStream[n2]);
                    ++n2;
                } while (true);
            }
            ++this.redundantOpCount;
            this.journalWriter.writeUtf8("READ").writeByte(32).writeUtf8(string2).writeByte(10);
            if (!this.journalRebuildRequired()) return new Snapshot(string2, entry.sequenceNumber, arrinputStream, entry.lengths);
            this.executorService.execute(this.cleanupRunnable);
            return new Snapshot(string2, entry.sequenceNumber, arrinputStream, entry.lengths);
        }
    }

    public File getDirectory() {
        return this.directory;
    }

    public long getMaxSize() {
        synchronized (this) {
            long l2 = this.maxSize;
            return l2;
        }
    }

    public boolean isClosed() {
        if (this.journalWriter == null) {
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean remove(String string2) throws IOException {
        synchronized (this) {
            Entry entry;
            block7 : {
                this.checkNotClosed();
                this.validateKey(string2);
                entry = this.lruEntries.get(string2);
                if (entry == null) return false;
                Editor editor = entry.currentEditor;
                if (editor == null) break block7;
                return false;
            }
            int n2 = 0;
            do {
                if (n2 >= this.valueCount) break;
                DiskLruCache.deleteIfExists(entry.getCleanFile(n2));
                this.size -= entry.lengths[n2];
                Entry.access$1000((Entry)entry)[n2] = 0;
                ++n2;
            } while (true);
            ++this.redundantOpCount;
            this.journalWriter.writeUtf8("REMOVE").writeByte(32).writeUtf8(string2).writeByte(10);
            this.lruEntries.remove(string2);
            if (!this.journalRebuildRequired()) return true;
            this.executorService.execute(this.cleanupRunnable);
            return true;
        }
    }

    public void setMaxSize(long l2) {
        synchronized (this) {
            this.maxSize = l2;
            this.executorService.execute(this.cleanupRunnable);
            return;
        }
    }

    public long size() {
        synchronized (this) {
            long l2 = this.size;
            return l2;
        }
    }

    public final class Editor {
        private boolean committed;
        private final Entry entry;
        private boolean hasErrors;
        private final boolean[] written;

        /*
         * Enabled aggressive block sorting
         */
        private Editor(Entry entry) {
            void var2_5;
            void var1_3;
            this.entry = var2_5;
            if (((Entry)var2_5).readable) {
                Object var1_2 = null;
            } else {
                boolean[] arrbl = new boolean[DiskLruCache.this.valueCount];
            }
            this.written = var1_3;
        }

        static /* synthetic */ Entry access$1400(Editor editor) {
            return editor.entry;
        }

        static /* synthetic */ boolean[] access$1500(Editor editor) {
            return editor.written;
        }

        public void abort() throws IOException {
            DiskLruCache.this.completeEdit(this, false);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public void abortUnlessCommitted() {
            if (this.committed) return;
            try {
                this.abort();
                return;
            }
            catch (IOException var1_1) {
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        public void commit() throws IOException {
            if (this.hasErrors) {
                DiskLruCache.this.completeEdit(this, false);
                DiskLruCache.this.remove(this.entry.key);
            } else {
                DiskLruCache.this.completeEdit(this, true);
            }
            this.committed = true;
        }

        public String getString(int n2) throws IOException {
            InputStream inputStream = this.newInputStream(n2);
            if (inputStream != null) {
                return DiskLruCache.inputStreamToString(inputStream);
            }
            return null;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public InputStream newInputStream(int n2) throws IOException {
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                }
                if (!this.entry.readable) {
                    return null;
                }
                try {
                    return new FileInputStream(this.entry.getCleanFile(n2));
                }
                catch (FileNotFoundException var3_4) {
                    return null;
                }
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public OutputStream newOutputStream(int n2) throws IOException {
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                OutputStream outputStream2;
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                }
                if (!this.entry.readable) {
                    this.written[n2] = true;
                }
                File file = this.entry.getDirtyFile(n2);
                try {
                    outputStream2 = new FileOutputStream(file);
                    return new FaultHidingOutputStream(outputStream2);
                }
                catch (FileNotFoundException var2_5) {
                    OutputStream outputStream2;
                    DiskLruCache.this.directory.mkdirs();
                    try {
                        outputStream2 = new FileOutputStream(file);
                    }
                    catch (FileNotFoundException var2_6) {
                        return NULL_OUTPUT_STREAM;
                    }
                    return new FaultHidingOutputStream(outputStream2);
                }
            }
        }

        public void set(int n2, String string2) throws IOException {
            BufferedSink bufferedSink = Okio.buffer(Okio.sink(this.newOutputStream(n2)));
            bufferedSink.writeUtf8(string2);
            bufferedSink.close();
        }

        private class FaultHidingOutputStream
        extends FilterOutputStream {
            private FaultHidingOutputStream(OutputStream outputStream) {
                super(outputStream);
            }

            @Override
            public void close() {
                try {
                    this.out.close();
                    return;
                }
                catch (IOException var1_1) {
                    Editor.this.hasErrors = true;
                    return;
                }
            }

            @Override
            public void flush() {
                try {
                    this.out.flush();
                    return;
                }
                catch (IOException var1_1) {
                    Editor.this.hasErrors = true;
                    return;
                }
            }

            @Override
            public void write(int n2) {
                try {
                    this.out.write(n2);
                    return;
                }
                catch (IOException var2_2) {
                    Editor.this.hasErrors = true;
                    return;
                }
            }

            @Override
            public void write(byte[] arrby, int n2, int n3) {
                try {
                    this.out.write(arrby, n2, n3);
                    return;
                }
                catch (IOException var1_2) {
                    Editor.this.hasErrors = true;
                    return;
                }
            }
        }

    }

    private final class Entry {
        private Editor currentEditor;
        private final String key;
        private final long[] lengths;
        private boolean readable;
        private long sequenceNumber;

        private Entry(String string2) {
            this.key = string2;
            this.lengths = new long[DiskLruCache.this.valueCount];
        }

        static /* synthetic */ long access$1202(Entry entry, long l2) {
            entry.sequenceNumber = l2;
            return l2;
        }

        private IOException invalidLengths(String[] arrstring) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(arrstring));
        }

        private void setLengths(String[] arrstring) throws IOException {
            if (arrstring.length != DiskLruCache.this.valueCount) {
                throw this.invalidLengths(arrstring);
            }
            int n2 = 0;
            do {
                try {
                    if (n2 >= arrstring.length) break;
                    this.lengths[n2] = Long.parseLong(arrstring[n2]);
                    ++n2;
                    continue;
                }
                catch (NumberFormatException var3_3) {
                    throw this.invalidLengths(arrstring);
                }
            } while (true);
        }

        public File getCleanFile(int n2) {
            return new File(DiskLruCache.this.directory, this.key + "." + n2);
        }

        public File getDirtyFile(int n2) {
            return new File(DiskLruCache.this.directory, this.key + "." + n2 + ".tmp");
        }

        public String getLengths() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long l2 : this.lengths) {
                stringBuilder.append(' ').append(l2);
            }
            return stringBuilder.toString();
        }
    }

    public final class Snapshot
    implements Closeable {
        private final InputStream[] ins;
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;

        private Snapshot(String string2, long l2, InputStream[] arrinputStream, long[] arrl) {
            this.key = string2;
            this.sequenceNumber = l2;
            this.ins = arrinputStream;
            this.lengths = arrl;
        }

        @Override
        public void close() {
            InputStream[] arrinputStream = this.ins;
            int n2 = arrinputStream.length;
            for (int i2 = 0; i2 < n2; ++i2) {
                Util.closeQuietly(arrinputStream[i2]);
            }
        }

        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public InputStream getInputStream(int n2) {
            return this.ins[n2];
        }

        public long getLength(int n2) {
            return this.lengths[n2];
        }

        public String getString(int n2) throws IOException {
            return DiskLruCache.inputStreamToString(this.getInputStream(n2));
        }
    }

}

