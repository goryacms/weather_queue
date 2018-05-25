package ru.bellintegrator.weatherqueue.jms.dao;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.bellintegrator.weatherqueue.Application;
import ru.bellintegrator.weatherqueue.jms.dao.impl.JmsDaoImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@DirtiesContext
public class JmsDaoTest {

    @Autowired
    private JmsDao jmsDao;

    @Test
    public void loadByCity(){
        String city = "Пенза";

        String res = jmsDao.loadByCity(city);

        //System.out.println(res.readerFor());

    }


    public static void main(String[] args) {
        String city = "Penza";
        JmsDao jmsDao = new JmsDaoImpl();

        String resp = jmsDao.loadByCity(city);

        System.out.println(resp);

        //JsonParser jsonParser = new JsonParser();
        //JsonElement element = jsonParser.parse(resp);

        ObjectMapper objMapper = new ObjectMapper();
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//        try {
//            JmsView view = objMapper.readValue(resp, JmsView.class);
//            System.out.println(view.lang);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //JmsView cls = res.readerFor(JmsView.class);

        Object ob = new Object();




    }



}
