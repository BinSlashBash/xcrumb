package com.fasterxml.jackson.databind.jsontype;

import java.io.Serializable;

public final class NamedType
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final Class<?> _class;
  protected final int _hashCode;
  protected String _name;
  
  public NamedType(Class<?> paramClass)
  {
    this(paramClass, null);
  }
  
  public NamedType(Class<?> paramClass, String paramString)
  {
    this._class = paramClass;
    this._hashCode = paramClass.getName().hashCode();
    setName(paramString);
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (paramObject.getClass() != getClass()) {
        return false;
      }
    } while (this._class == ((NamedType)paramObject)._class);
    return false;
  }
  
  public String getName()
  {
    return this._name;
  }
  
  public Class<?> getType()
  {
    return this._class;
  }
  
  public boolean hasName()
  {
    return this._name != null;
  }
  
  public int hashCode()
  {
    return this._hashCode;
  }
  
  public void setName(String paramString)
  {
    String str;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.length() != 0) {}
    }
    else
    {
      str = null;
    }
    this._name = str;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("[NamedType, class ").append(this._class.getName()).append(", name: ");
    if (this._name == null) {}
    for (String str = "null";; str = "'" + this._name + "'") {
      return str + "]";
    }
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/fasterxml/jackson/databind/jsontype/NamedType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */