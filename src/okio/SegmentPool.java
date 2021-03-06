package okio;

final class SegmentPool
{
  static final SegmentPool INSTANCE = new SegmentPool();
  static final long MAX_SIZE = 65536L;
  long byteCount;
  private Segment next;
  
  void recycle(Segment paramSegment)
  {
    if ((paramSegment.next != null) || (paramSegment.prev != null)) {
      throw new IllegalArgumentException();
    }
    try
    {
      if (this.byteCount + 2048L > 65536L) {
        return;
      }
      this.byteCount += 2048L;
      paramSegment.next = this.next;
      paramSegment.limit = 0;
      paramSegment.pos = 0;
      this.next = paramSegment;
      return;
    }
    finally {}
  }
  
  Segment take()
  {
    try
    {
      if (this.next != null)
      {
        Segment localSegment = this.next;
        this.next = localSegment.next;
        localSegment.next = null;
        this.byteCount -= 2048L;
        return localSegment;
      }
      return new Segment();
    }
    finally {}
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/okio/SegmentPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */