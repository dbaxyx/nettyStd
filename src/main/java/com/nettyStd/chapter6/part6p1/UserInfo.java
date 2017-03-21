package com.nettyStd.chapter6.part6p1;

import io.netty.buffer.ByteBuf;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * Created by dba on 2017/3/20.
 */
/*代码清单6-1*/
public class UserInfo implements Serializable {
  /*默认的序列号*/
  private static final long seriaVersionUID = 1L;

  private String userName;

  private int userID;

  private int age;

  public UserInfo() {
  }

  public int getAge() {

    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public UserInfo(String userName, int userID, int age) {

    this.userName = userName;
    this.userID = userID;
    this.age = age;
  }

  public UserInfo buildUserName(String userName) {
    this.userName = userName;
    return  this;
  }

  public UserInfo buildUserID(int userID) {
      this.userID = userID;
      return this;
  }

    public final String getUserName() {
        return userName;
    }

    public final void setUserName(String userName) {
        this.userName = userName;
    }

    public final int getUserID() {
        return userID;
    }

    public final void setUserID(int userID) {
        this.userID = userID;
    }

    public byte[] codeC() {
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      byte[] value = this.userName.getBytes();
      buffer.putInt(value.length);
      buffer.put(value);
      buffer.putInt(this.userID);
      buffer.flip();
      value = null;
      byte[] result = new byte[buffer.remaining()];
      buffer.get(result);
      return result;
    }

    public byte[] codeC(ByteBuffer buffer) {
      buffer.clear();
      byte[] value = this.userName.getBytes();
      buffer.putInt(value.length);
      buffer.put(value);
      buffer.putInt(this.userID);
      buffer.flip();
      value = null;
      byte[] result = new byte[buffer.remaining()];
      buffer.get(result);
      return result;
    }
}
