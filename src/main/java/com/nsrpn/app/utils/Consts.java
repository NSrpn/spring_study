package com.nsrpn.app.utils;

public class Consts {
  public static class Queries {
    public final static String getAll = "qry.getAll";

    public static String getQry(String qry, Class cls) {
      return qry.replaceAll("%cls%", cls.getName());
    }
  }
}
