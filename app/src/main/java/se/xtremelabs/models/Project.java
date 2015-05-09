package se.xtremelabs.models;

import android.util.Log;

import com.google.gson.Gson;
import com.orm.SugarRecord;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by erik on 2015-04-14.
 *
 * A <Project> Project </Project> is an umbrella for a set of
 * <Inspection> Inspections </Inspection> performed by an Operator.
 *
 * Project is also available to use with the database schema,
 * hence the inheritance by SugarRecord
 *
 *
 */

public class Project extends SugarRecord<Project> {

    public String name;
    public Client client;
    public String address;
    public Date start_time;
    public Date end_time;
    public Date last_synced; //Updates when uploaded to oc.
    public String status;
    public String datafolder;
    public String info;
    public String image_map;
    public String metric_system;

}
