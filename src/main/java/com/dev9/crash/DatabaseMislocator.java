package com.dev9.crash;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMislocator {

    static String driver = "org.hsqldb.jdbcDriver";
    static String url = "jdbc:hsqldb:mem:example";
    static String username = "sa";
    static String password = "";

    static public List<BadThing> getBadThings() {
        BadThing bt1 = new BadThing() {

            public String badThingDescription() {
                return "Looks for a made-up driver.";
            }

            public String badThingName() {
                return "Mythical JDBC Driver";
            }

            public String doBadThing() throws Exception {
                new DatabaseMislocator().testCantFindDriver();
                return null;
            }
        };

        BadThing bt2 = new BadThing() {

            public String badThingDescription() {
                return "Looks for a meaningless JDBC connection string for a valid (hsqldb) driver.";
            }

            public String badThingName() {
                return "Gibberish JDBC URL";
            }

            public String doBadThing() throws Exception {
                new DatabaseMislocator().testCantFindURL();
                return null;
            }
        };
        BadThing bt3 = new BadThing() {

            public String badThingDescription() {
                String result = "Looks for a valid driver (" + driver
                        + ") and connection string (" + url
                        + ") using username/password (" + username + ","
                        + password + ") and execute meaningless SQL. ";
                return result;
            }

            public String badThingName() {
                return "Execute Meaningless SQL (SELECT asdf)";
            }

            public String doBadThing() throws Exception {
                new DatabaseMislocator().nonsensicalSQL();
                return null;
            }
        };
        BadThing bt4 = new BadThing() {

            public String badThingDescription() {
                String result = "Looks for a valid driver ("
                        + driver
                        + ") and connection string ("
                        + url
                        + ") using username/password ("
                        + username
                        + ","
                        + password
                        + ") and opens as many connections as possible. "
                        + "WARNING: May lock up your GUI.";
                return result;
            }

            public String badThingName() {
                return "Opens connections until death";
            }

            public String doBadThing() throws Exception {
                new DatabaseMislocator().openDBConnectionsTillCrash();
                return null;
            }
        };

        List<BadThing> result = new ArrayList<BadThing>();
        result.add(bt1);
        result.add(bt2);
        result.add(bt3);
        result.add(bt4);

        return result;
    }

    public static void read(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int colmax = meta.getColumnCount();
        int i;

        while (rs.next()) {
            for (i = 0; i < colmax; ++i) {
                rs.getObject(i + 1);
            }
        }
    }

    public void testCantFindDriver() throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        Class.forName("not.a.real.driver").newInstance();
    }

    public void testCantFindURL() throws SQLException {
        @SuppressWarnings("unused")
        Connection c = DriverManager.getConnection("jdbc:hsqldb:blahblahblah",
                "sa", "");
    }

    public void executeSQL(String query) throws Exception {
        Class.forName(driver).newInstance();

        Connection c = DriverManager.getConnection(url, username, password);

        Statement st = null;
        ResultSet rs = null;

        Exception e = null;
        try {
            st = c.createStatement();

            rs = st.executeQuery(query);

            read(rs);
        } catch (Exception e1) {
            e = e1;
        } finally {
            if (st != null)
                st.close();
            if (e != null)
                throw e;
        }
    }

    public void nonsensicalSQL() throws Exception {
        executeSQL("SELECT asdf");
    }

    public void openDBConnectionsTillCrash() throws Exception {
        Class.forName(driver).newInstance();

        while (true) {
            Connection c = DriverManager.getConnection(url, username, password);

            Statement st = null;
            ResultSet rs = null;

            st = c.createStatement();

            rs = st.executeQuery("select 2 + 2 as result");

            read(rs);
        }
    }

}
