package ru.bellintegrator.weatherqueue.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yql4j.*;
import ru.bellintegrator.weatherqueue.jms.service.JmsService;
import ru.bellintegrator.weatherqueue.jms.view.JmsView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@RestController
@RequestMapping(value = "/api")
public class JmsController {
    private JmsService jmsService;

    @Autowired
    public JmsController(JmsService jmsService) {
        this.jmsService = jmsService;
    }


    @RequestMapping(value = "/jms", method = RequestMethod.POST)
    public JmsView weatherData(@RequestBody String city) throws IOException {
        System.out.println(city);
        JmsView view = jmsService.loadByCity(city);
        System.out.println(view.lang+"**"+view.created+"**"+view.count);
        return view;
    }



//    public static void main(String[] args) throws IOException {
//        System.out.println(getWithLib());
//        //System.out.println(getWithoutLib());
//    }
//
//    private static String getWithLib(){
//        YqlClient client = YqlClients.createDefault();
//        YqlQuery query = YqlQueryBuilder
//                .fromQueryString("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=@name)")
//                .withVariable("name", "Токио").withFormat(ResultFormat.JSON).build();
//
//        YqlResult result = null;
//
//        try {
//            result = client.query(query);
//        } catch (YqlException e) {
//            e.printStackTrace();
//        }
//
//        // Now you can do whatever you like with the raw result
//        String rawResult = result.getContentAsString();
//
//
//
//        return rawResult;
//
//        // But if you are lazy, you may also get the content mapped as object graph
//    // Please note though: You will have to provide your own mapping classes,
//    // i.e. PlaceArrayType and PlaceType!
//
//
//
//    //    QueryResultType<PlaceArrayType> mappedResult =
//    //            result.getContentAsMappedObject(
//    //                    new TypeReference<QueryResultType<PlaceArrayType>>() {});
//    //    for (PlaceType item : mappedResult.getResults().getPlace()) {
//    //        // Do something with the item
//    //    }
//    }
//
//    private static String getWithoutLib() throws IOException {
//        String url = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22AAPL%22)&env=store://datatables.org/alltableswithkeys&format=json";
//
//        // Create a URL and open a connection
//        URL YahooUrl = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) YahooUrl.openConnection();
//
//        // Set the HTTP Request type method to GET (Default: GET)
//        con.setRequestMethod("GET");
//
//        // Created a BufferedReader to read the contents of the request.
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuilder response = new StringBuilder();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//
//        // MAKE SURE TO CLOSE YOUR CONNECTION!
//        in.close();
//
//        // response is the contents of the XML
//        return response.toString();
//    }



}
