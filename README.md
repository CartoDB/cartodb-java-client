#CartoDB Java Client

##Description
Tiny CartoDB Java client that can be instantiated as regular(public) or secured(private). Then, you can 
send queries to CartoDB and get a JSON string as response.

##Usage

    //Access to your public table
    CartoDBClientIF cartoDBCLient= new CartoDBClient("youraccount");
    System.out.println(cartoDBCLient.executeQuery("SELECT * FROM yourtable LIMIT 1"));

    //Access to your private table or update your table
    CartoDBClientIF cartoDBCLient= new SecuredCartoDBClient("youraccount","yourpassword","consumerKey","consumerSecret");
    System.out.println(cartoDBCLient.executeQuery("UPDATE yourtable SET yourvalue = 'test' WHERE yourid = 1"));

###Use resulting JSON as Java object
To transform your JSON string into real Java object, you can use a combination of [json.org](http://json.org/java/) and [Apache Bean utils](http://commons.apache.org/beanutils/).
You could also use Jackson`s ObjectMapper, easier to use but a little bit slower(based on my own test) on very simple JSON string.
Here is an example using Jackson's ObjectMapper:

    //this object is expansive to create so keep the reference
    ObjectMapper jacksonMapper = new ObjectMapper();
    CartoDBResponse<OccurrenceModel> response = jacksonMapper.readValue(json, new TypeReference<CartoDBResponse<OccurrenceModel>>(){});

My CartoDBResponse is now filled with a List of OccurrenceModel object, which represents the result of my query. Note that OccurrenceModel need to follows JavaBean specification. TypeReference is only a Wrapper to help the ObjectMapper manage Java Generics. 

##Dependencies
_Included in lib folder_

* [Scribe](https://github.com/fernandezpablo85/scribe-java)
* [Apache commons codec](http://commons.apache.org/codec/)
* [Apache commons IO](http://commons.apache.org/io/)

##TODO
* Include error JSON on HTTP response 400.
* executeQuery(sql) with a List of Java beans as return type
