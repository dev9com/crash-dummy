package com.dev9.crash.bad;

import com.dev9.crash.BadThing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMislocator {

    static String driver = "org.hsqldb.jdbcDriver";
    static String url = "jdbc:hsqldb:mem:example";
    static String username = "sa";
    static String password = "";

    static public List<BadThing> getBadThings() {
        BadThing bt1 = new BogusJdbcDriver();

        BadThing bt2 = new MeaninglessJdbcConnectionString();
        BadThing bt3 = new ExecuteBadSql();
        BadThing bt4 = new OpenJdbcConnUntilDie();

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
