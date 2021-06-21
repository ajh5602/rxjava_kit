package com.ash.retrofit.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public final class Config {

    private static final String TAG = Config.class.getSimpleName();
    private static final String FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/door_config.cfg";
    private static Config sInstance = null;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Config getInstance() {
        if (sInstance == null) {
            synchronized (Config.class) {
                if (sInstance == null) {
                    sInstance = new Config();
                }
            }
        }
        return sInstance;
    }

    /**
     * Write configurations values boolean.
     *
     * @return the boolean
     */
    public boolean writeConfigurationsValues(String key,String vals) {

        try (OutputStream output = new FileOutputStream(FILE_PATH)) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty(key, vals);
            // save properties
            prop.store(output, null);

            Log.i(TAG, "Configuration stored  properties: " + prop);
            return true;
        } catch (IOException io) {
            io.printStackTrace();
            return false;
        }
    }
    public boolean writeConfigurationsValues(Hashtable ht) {

        try (OutputStream output = new FileOutputStream(FILE_PATH)) {

            Properties prop = new Properties();
            Set<String> keys = ht.keySet();
            Iterator<String> itr = keys.iterator();
            String str="";
            while (itr.hasNext()) {
                // Getting Key
                str = itr.next();
                prop.setProperty(str, (String) ht.get(str));
            }
            // set the properties value

            // save properties
            prop.store(output, null);

            Log.i(TAG, "Configuration stored  properties: " + prop);
            return true;
        } catch (IOException io) {
            io.printStackTrace();
            return false;
        }
    }

    /**
     * Get configuration value string.
     *
     * @param key the key
     * @return the string
     */
    public String getConfigurationValue(String key){
        String value = "";
        try (InputStream input = new FileInputStream(FILE_PATH)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            value = prop.getProperty(key);
            Log.i(TAG, "Configuration stored  properties value: " + value);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return value;
    }

    public boolean checkConfigrationFile() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        File[] files = file.listFiles();
        Log.d(TAG, "checkConfigrationFile: files.length = " + files.length);
        File aFile = null;
        try {
            for (File tempFile : files) {
                Log.d("MyTag", tempFile.getName());
                if ("door_config.cfg".equals(tempFile.getName())) {
                    aFile = tempFile;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != aFile) return true;
            else return false;
        }

    }

}