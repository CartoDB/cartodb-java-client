#CartoDB Java Client

##Description
Tiny CartoDB Java client that can be instantiated as regular(public) or secured(private).

##Usage

    //Access to your public table
    CartoDBClientIF cartoDBCLient= new CartoDBClient("youraccount");
    System.out.println(cartoDBCLient.executeQuery("SELECT * FROM yourtable LIMIT 1"));

    //Access to your private table or update your table
    CartoDBClientIF cartoDBCLient= new SecuredCartoDBClient("youraccount","yourpassword","consumerKey","consumerSecret");
    System.out.println(cartoDBCLient.executeQuery("UPDATE yourtable SET yourvalue = 'test' WHERE yourid = 1"));


##TODO
* Provide an Ant or Maven file
* executeQuery(sql) with a List of Java beans as return type
