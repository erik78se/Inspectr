package se.xtremelabs.models;

import com.orm.SugarRecord;

/**
 * Created by erik on 2015-04-27.
 */
public class Client extends SugarRecord<Client> {

    public String name;
    public String telephone_number;
    public String email;

}
