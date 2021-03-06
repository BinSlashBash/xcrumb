package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import java.util.HashMap;

public final class SerializerCache
{
  private volatile ReadOnlyClassToSerializerMap _readOnlyMap = null;
  private HashMap<TypeKey, JsonSerializer<Object>> _sharedMap = new HashMap(64);
  
  public void addAndResolveNonTypedSerializer(JavaType paramJavaType, JsonSerializer<Object> paramJsonSerializer, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    try
    {
      if (this._sharedMap.put(new TypeKey(paramJavaType, false), paramJsonSerializer) == null) {
        this._readOnlyMap = null;
      }
      if ((paramJsonSerializer instanceof ResolvableSerializer)) {
        ((ResolvableSerializer)paramJsonSerializer).resolve(paramSerializerProvider);
      }
      return;
    }
    finally {}
  }
  
  public void addAndResolveNonTypedSerializer(Class<?> paramClass, JsonSerializer<Object> paramJsonSerializer, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    try
    {
      if (this._sharedMap.put(new TypeKey(paramClass, false), paramJsonSerializer) == null) {
        this._readOnlyMap = null;
      }
      if ((paramJsonSerializer instanceof ResolvableSerializer)) {
        ((ResolvableSerializer)paramJsonSerializer).resolve(paramSerializerProvider);
      }
      return;
    }
    finally {}
  }
  
  public void addTypedSerializer(JavaType paramJavaType, JsonSerializer<Object> paramJsonSerializer)
  {
    try
    {
      if (this._sharedMap.put(new TypeKey(paramJavaType, true), paramJsonSerializer) == null) {
        this._readOnlyMap = null;
      }
      return;
    }
    finally {}
  }
  
  public void addTypedSerializer(Class<?> paramClass, JsonSerializer<Object> paramJsonSerializer)
  {
    try
    {
      if (this._sharedMap.put(new TypeKey(paramClass, true), paramJsonSerializer) == null) {
        this._readOnlyMap = null;
      }
      return;
    }
    finally {}
  }
  
  public void flush()
  {
    try
    {
      this._sharedMap.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public ReadOnlyClassToSerializerMap getReadOnlyLookupMap()
  {
    ReadOnlyClassToSerializerMap localReadOnlyClassToSerializerMap2 = this._readOnlyMap;
    ReadOnlyClassToSerializerMap localReadOnlyClassToSerializerMap1 = localReadOnlyClassToSerializerMap2;
    if (localReadOnlyClassToSerializerMap2 == null) {}
    try
    {
      localReadOnlyClassToSerializerMap2 = this._readOnlyMap;
      localReadOnlyClassToSerializerMap1 = localReadOnlyClassToSerializerMap2;
      if (localReadOnlyClassToSerializerMap2 == null)
      {
        localReadOnlyClassToSerializerMap1 = ReadOnlyClassToSerializerMap.from(this._sharedMap);
        this._readOnlyMap = localReadOnlyClassToSerializerMap1;
      }
      return localReadOnlyClassToSerializerMap1.instance();
    }
    finally {}
  }
  
  public int size()
  {
    try
    {
      int i = this._sharedMap.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public JsonSerializer<Object> typedValueSerializer(JavaType paramJavaType)
  {
    try
    {
      paramJavaType = (JsonSerializer)this._sharedMap.get(new TypeKey(paramJavaType, true));
      return paramJavaType;
    }
    finally {}
  }
  
  public JsonSerializer<Object> typedValueSerializer(Class<?> paramClass)
  {
    try
    {
      paramClass = (JsonSerializer)this._sharedMap.get(new TypeKey(paramClass, true));
      return paramClass;
    }
    finally {}
  }
  
  public JsonSerializer<Object> untypedValueSerializer(JavaType paramJavaType)
  {
    try
    {
      paramJavaType = (JsonSerializer)this._sharedMap.get(new TypeKey(paramJavaType, false));
      return paramJavaType;
    }
    finally {}
  }
  
  public JsonSerializer<Object> untypedValueSerializer(Class<?> paramClass)
  {
    try
    {
      paramClass = (JsonSerializer)this._sharedMap.get(new TypeKey(paramClass, false));
      return paramClass;
    }
    finally {}
  }
  
  public static final class TypeKey
  {
    protected Class<?> _class;
    protected int _hashCode;
    protected boolean _isTyped;
    protected JavaType _type;
    
    public TypeKey(JavaType paramJavaType, boolean paramBoolean)
    {
      this._type = paramJavaType;
      this._class = null;
      this._isTyped = paramBoolean;
      this._hashCode = hash(paramJavaType, paramBoolean);
    }
    
    public TypeKey(Class<?> paramClass, boolean paramBoolean)
    {
      this._class = paramClass;
      this._type = null;
      this._isTyped = paramBoolean;
      this._hashCode = hash(paramClass, paramBoolean);
    }
    
    private static final int hash(JavaType paramJavaType, boolean paramBoolean)
    {
      int j = paramJavaType.hashCode() - 1;
      int i = j;
      if (paramBoolean) {
        i = j - 1;
      }
      return i;
    }
    
    private static final int hash(Class<?> paramClass, boolean paramBoolean)
    {
      int j = paramClass.getName().hashCode();
      int i = j;
      if (paramBoolean) {
        i = j + 1;
      }
      return i;
    }
    
    public final boolean equals(Object paramObject)
    {
      boolean bool = true;
      if (paramObject == null) {}
      do
      {
        do
        {
          return false;
          if (paramObject == this) {
            return true;
          }
        } while (paramObject.getClass() != getClass());
        paramObject = (TypeKey)paramObject;
      } while (((TypeKey)paramObject)._isTyped != this._isTyped);
      if (this._class != null)
      {
        if (((TypeKey)paramObject)._class == this._class) {}
        for (;;)
        {
          return bool;
          bool = false;
        }
      }
      return this._type.equals(((TypeKey)paramObject)._type);
    }
    
    public final int hashCode()
    {
      return this._hashCode;
    }
    
    public void resetTyped(JavaType paramJavaType)
    {
      this._type = paramJavaType;
      this._class = null;
      this._isTyped = true;
      this._hashCode = hash(paramJavaType, true);
    }
    
    public void resetTyped(Class<?> paramClass)
    {
      this._type = null;
      this._class = paramClass;
      this._isTyped = true;
      this._hashCode = hash(paramClass, true);
    }
    
    public void resetUntyped(JavaType paramJavaType)
    {
      this._type = paramJavaType;
      this._class = null;
      this._isTyped = false;
      this._hashCode = hash(paramJavaType, false);
    }
    
    public void resetUntyped(Class<?> paramClass)
    {
      this._type = null;
      this._class = paramClass;
      this._isTyped = false;
      this._hashCode = hash(paramClass, false);
    }
    
    public final String toString()
    {
      if (this._class != null) {
        return "{class: " + this._class.getName() + ", typed? " + this._isTyped + "}";
      }
      return "{type: " + this._type + ", typed? " + this._isTyped + "}";
    }
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/fasterxml/jackson/databind/ser/SerializerCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */