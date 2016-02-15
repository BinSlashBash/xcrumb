package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class DefaultPrettyPrinter
  implements PrettyPrinter, Instantiatable<DefaultPrettyPrinter>, Serializable
{
  public static final SerializedString DEFAULT_ROOT_VALUE_SEPARATOR = new SerializedString(" ");
  private static final long serialVersionUID = -5512586643324525213L;
  protected Indenter _arrayIndenter = FixedSpaceIndenter.instance;
  protected transient int _nesting = 0;
  protected Indenter _objectIndenter = Lf2SpacesIndenter.instance;
  protected final SerializableString _rootSeparator;
  protected boolean _spacesInObjectEntries = true;
  
  public DefaultPrettyPrinter()
  {
    this(DEFAULT_ROOT_VALUE_SEPARATOR);
  }
  
  public DefaultPrettyPrinter(SerializableString paramSerializableString)
  {
    this._rootSeparator = paramSerializableString;
  }
  
  public DefaultPrettyPrinter(DefaultPrettyPrinter paramDefaultPrettyPrinter)
  {
    this(paramDefaultPrettyPrinter, paramDefaultPrettyPrinter._rootSeparator);
  }
  
  public DefaultPrettyPrinter(DefaultPrettyPrinter paramDefaultPrettyPrinter, SerializableString paramSerializableString)
  {
    this._arrayIndenter = paramDefaultPrettyPrinter._arrayIndenter;
    this._objectIndenter = paramDefaultPrettyPrinter._objectIndenter;
    this._spacesInObjectEntries = paramDefaultPrettyPrinter._spacesInObjectEntries;
    this._nesting = paramDefaultPrettyPrinter._nesting;
    this._rootSeparator = paramSerializableString;
  }
  
  public DefaultPrettyPrinter(String paramString) {}
  
  protected DefaultPrettyPrinter _withSpaces(boolean paramBoolean)
  {
    if (this._spacesInObjectEntries == paramBoolean) {
      return this;
    }
    DefaultPrettyPrinter localDefaultPrettyPrinter = new DefaultPrettyPrinter(this);
    localDefaultPrettyPrinter._spacesInObjectEntries = paramBoolean;
    return localDefaultPrettyPrinter;
  }
  
  public void beforeArrayValues(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    this._arrayIndenter.writeIndentation(paramJsonGenerator, this._nesting);
  }
  
  public void beforeObjectEntries(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    this._objectIndenter.writeIndentation(paramJsonGenerator, this._nesting);
  }
  
  public DefaultPrettyPrinter createInstance()
  {
    return new DefaultPrettyPrinter(this);
  }
  
  public void indentArraysWith(Indenter paramIndenter)
  {
    Object localObject = paramIndenter;
    if (paramIndenter == null) {
      localObject = NopIndenter.instance;
    }
    this._arrayIndenter = ((Indenter)localObject);
  }
  
  public void indentObjectsWith(Indenter paramIndenter)
  {
    Object localObject = paramIndenter;
    if (paramIndenter == null) {
      localObject = NopIndenter.instance;
    }
    this._objectIndenter = ((Indenter)localObject);
  }
  
  @Deprecated
  public void spacesInObjectEntries(boolean paramBoolean)
  {
    this._spacesInObjectEntries = paramBoolean;
  }
  
  public DefaultPrettyPrinter withArrayIndenter(Indenter paramIndenter)
  {
    Object localObject = paramIndenter;
    if (paramIndenter == null) {
      localObject = NopIndenter.instance;
    }
    if (this._arrayIndenter == localObject) {
      return this;
    }
    paramIndenter = new DefaultPrettyPrinter(this);
    paramIndenter._arrayIndenter = ((Indenter)localObject);
    return paramIndenter;
  }
  
  public DefaultPrettyPrinter withObjectIndenter(Indenter paramIndenter)
  {
    Object localObject = paramIndenter;
    if (paramIndenter == null) {
      localObject = NopIndenter.instance;
    }
    if (this._objectIndenter == localObject) {
      return this;
    }
    paramIndenter = new DefaultPrettyPrinter(this);
    paramIndenter._objectIndenter = ((Indenter)localObject);
    return paramIndenter;
  }
  
  public DefaultPrettyPrinter withRootSeparator(SerializableString paramSerializableString)
  {
    if ((this._rootSeparator == paramSerializableString) || ((paramSerializableString != null) && (paramSerializableString.equals(this._rootSeparator)))) {
      return this;
    }
    return new DefaultPrettyPrinter(this, paramSerializableString);
  }
  
  public DefaultPrettyPrinter withSpacesInObjectEntries()
  {
    return _withSpaces(true);
  }
  
  public DefaultPrettyPrinter withoutSpacesInObjectEntries()
  {
    return _withSpaces(false);
  }
  
  public void writeArrayValueSeparator(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeRaw(',');
    this._arrayIndenter.writeIndentation(paramJsonGenerator, this._nesting);
  }
  
  public void writeEndArray(JsonGenerator paramJsonGenerator, int paramInt)
    throws IOException, JsonGenerationException
  {
    if (!this._arrayIndenter.isInline()) {
      this._nesting -= 1;
    }
    if (paramInt > 0) {
      this._arrayIndenter.writeIndentation(paramJsonGenerator, this._nesting);
    }
    for (;;)
    {
      paramJsonGenerator.writeRaw(']');
      return;
      paramJsonGenerator.writeRaw(' ');
    }
  }
  
  public void writeEndObject(JsonGenerator paramJsonGenerator, int paramInt)
    throws IOException, JsonGenerationException
  {
    if (!this._objectIndenter.isInline()) {
      this._nesting -= 1;
    }
    if (paramInt > 0) {
      this._objectIndenter.writeIndentation(paramJsonGenerator, this._nesting);
    }
    for (;;)
    {
      paramJsonGenerator.writeRaw('}');
      return;
      paramJsonGenerator.writeRaw(' ');
    }
  }
  
  public void writeObjectEntrySeparator(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeRaw(',');
    this._objectIndenter.writeIndentation(paramJsonGenerator, this._nesting);
  }
  
  public void writeObjectFieldValueSeparator(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    if (this._spacesInObjectEntries)
    {
      paramJsonGenerator.writeRaw(" : ");
      return;
    }
    paramJsonGenerator.writeRaw(':');
  }
  
  public void writeRootValueSeparator(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    if (this._rootSeparator != null) {
      paramJsonGenerator.writeRaw(this._rootSeparator);
    }
  }
  
  public void writeStartArray(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    if (!this._arrayIndenter.isInline()) {
      this._nesting += 1;
    }
    paramJsonGenerator.writeRaw('[');
  }
  
  public void writeStartObject(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeRaw('{');
    if (!this._objectIndenter.isInline()) {
      this._nesting += 1;
    }
  }
  
  public static class FixedSpaceIndenter
    extends DefaultPrettyPrinter.NopIndenter
  {
    public static final FixedSpaceIndenter instance = new FixedSpaceIndenter();
    
    public boolean isInline()
    {
      return true;
    }
    
    public void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeRaw(' ');
    }
  }
  
  public static abstract interface Indenter
  {
    public abstract boolean isInline();
    
    public abstract void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt)
      throws IOException, JsonGenerationException;
  }
  
  public static class Lf2SpacesIndenter
    extends DefaultPrettyPrinter.NopIndenter
  {
    static final char[] SPACES;
    static final int SPACE_COUNT = 64;
    private static final String SYS_LF;
    public static final Lf2SpacesIndenter instance = new Lf2SpacesIndenter();
    protected final String _lf;
    
    static
    {
      Object localObject1 = null;
      try
      {
        localObject2 = System.getProperty("line.separator");
        localObject1 = localObject2;
      }
      catch (Throwable localThrowable)
      {
        Object localObject2;
        for (;;) {}
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = "\n";
      }
      SYS_LF = (String)localObject2;
      SPACES = new char[64];
      Arrays.fill(SPACES, ' ');
    }
    
    public Lf2SpacesIndenter()
    {
      this(SYS_LF);
    }
    
    public Lf2SpacesIndenter(String paramString)
    {
      this._lf = paramString;
    }
    
    public boolean isInline()
    {
      return false;
    }
    
    public Lf2SpacesIndenter withLinefeed(String paramString)
    {
      if (paramString.equals(this._lf)) {
        return this;
      }
      return new Lf2SpacesIndenter(paramString);
    }
    
    public void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeRaw(this._lf);
      if (paramInt > 0)
      {
        paramInt += paramInt;
        while (paramInt > 64)
        {
          paramJsonGenerator.writeRaw(SPACES, 0, 64);
          paramInt -= SPACES.length;
        }
        paramJsonGenerator.writeRaw(SPACES, 0, paramInt);
      }
    }
  }
  
  public static class NopIndenter
    implements DefaultPrettyPrinter.Indenter, Serializable
  {
    public static final NopIndenter instance = new NopIndenter();
    
    public boolean isInline()
    {
      return true;
    }
    
    public void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt)
      throws IOException, JsonGenerationException
    {}
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/fasterxml/jackson/core/util/DefaultPrettyPrinter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */