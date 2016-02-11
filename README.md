#CartoDB Java Client

##Description
Tiny CartoDB Java client that can be instantiated as regular (public) or secured (private). Once instantiated, you can 
send queries to CartoDB and get a JSON string as response.

##Building
In order to build a new JAR for this SDK you need to have installed [Gradle](http://gradle.org/gradle-download/) which is the building system.

Once you have Gradle installed, you must run ```gradle clean``` to remove previous builds and ```gradle build``` to generate the new JAR.

After the build process is finished you could find the new generated JAR in the ```gradleBuild/libs``` folder

##Usage

With this library you have access to private and public tables. In order to access to public tables you do not need to be authenticated
but if you want to read or write data to a private table or write data to a public table you need to.

CartoDB provides two ways to auth, oauth or api key, please read [CartoDB Authentication documentation](http://developers.cartodb.com/documentation/cartodb-apis.html#authentication) for detailed info on this topic.


### Using oAuth

    //Access to your private table or update your table
    CartoDBClientIF cartoDBCLient= new SecuredCartoDBClient("youraccount","yourpassword","consumerKey","consumerSecret");
    System.out.println(cartoDBCLient.executeQuery("UPDATE yourtable SET yourvalue = 'test' WHERE yourid = 1"));

### Using API key

    //Access to your private table or update your table using the api key
    CartoDBClientIF cartoDBCLient= new ApiKeyCartoDBClient("youraccount", YOUR_API_KEY);
    System.out.println(cartoDBCLient.executeQuery("UPDATE yourtable SET yourvalue = 'test' WHERE yourid = 1"));

### Access to Public tables (no auth required)

    //Access to your public table
    CartoDBClientIF cartoDBCLient= new CartoDBClient("youraccount");
    System.out.println(cartoDBCLient.executeQuery("SELECT * FROM yourtable LIMIT 1"));


### get results as java object

executeQuery method returns the raw json response. If you want to retrieve the json already parsed into a java object you can use request method, see next example:

    // get rows as a Map
    CartoDBResponse<Map<String, Object>> res = cartoDBCLient.request("select * from mytable limit 1");
    System.out.print(res.getTotal_rows(), 1);
    System.out.print(res.getRows().get(0).get("cartodb_id"));

##Dependencies
_Included in lib folder_

* [Scribe](https://github.com/fernandezpablo85/scribe-java)
* [Apache commons codec](http://commons.apache.org/codec/)
* [Apache commons IO](http://commons.apache.org/io/)
* [Jackson`s ObjectMapper](http://jackson.codehaus.org/)

##TODO
* Include error JSON on HTTP response 400.

## License
read LICENSE file
