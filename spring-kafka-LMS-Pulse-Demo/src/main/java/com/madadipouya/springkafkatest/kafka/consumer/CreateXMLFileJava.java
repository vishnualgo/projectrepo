package com.madadipouya.springkafkatest.kafka.consumer;


import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.madadipouya.springkafkatest.dto.MachineJobInfoRequest;
import com.madadipouya.springkafkatest.dto.PulseRequestModel;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateXMLFileJava {

    public static final String xmlFilePath = "D:\\tXML\\xmlfile.xml";

    public void createtXML(PulseRequestModel user){

        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            MachineJobInfoRequest machineJobInfoRequest = user.getJobs().get(0);
            // root element
            Element root = document.createElement("tXML");
            document.appendChild(root);

            // root element
            Element txmlMainRoot = document.createElement("root");
            root.appendChild(txmlMainRoot);

            // set an attribute to staff element
            //Attr attr = document.createAttribute("id");
            //attr.setValue(user.getUuid());
            //employee.setAttributeNode(attr);

            Element txmlRoot = document.createElement("jobs");
            txmlMainRoot.appendChild(txmlRoot);

            // facility element
            Element facility = document.createElement("facility");
            facility.appendChild(document.createTextNode(machineJobInfoRequest.getFacility()));
            txmlRoot.appendChild(facility);

            // jobId element
            Element jobId = document.createElement("jobId");
            jobId.appendChild(document.createTextNode(machineJobInfoRequest.getJobId()));
            txmlRoot.appendChild(jobId);

            // userId type element
            Element userId = document.createElement("userId");
            userId.appendChild(document.createTextNode(machineJobInfoRequest.getUserId()));
            txmlRoot.appendChild(userId);

            // logDate element
            Element logDate = document.createElement("logDate");
            logDate.appendChild(document.createTextNode(machineJobInfoRequest.getLogDate()));
            txmlRoot.appendChild(logDate);

            // action taken element
            Element actionTaken = document.createElement("actionTaken");
            actionTaken.appendChild(document.createTextNode(machineJobInfoRequest.getActionTaken()));
            txmlRoot.appendChild(actionTaken);

            // action taken element
            Element productCode = document.createElement("productCode");
            actionTaken.appendChild(document.createTextNode(machineJobInfoRequest.getProductCode()));
            txmlRoot.appendChild(productCode);

            // action taken element
            Element custOrderNumber = document.createElement("customerOrderNumber");
            actionTaken.appendChild(document.createTextNode(machineJobInfoRequest.getCustomerOrderNumber()));
            txmlRoot.appendChild(custOrderNumber);

            // department elements
            Element machineInfo = document.createElement("machineInfo");
            //machineInfo.appendChild(document.createTextNode("PREHL"));
            txmlRoot.appendChild(machineInfo);

            // action taken element
            Element pzIndicator = document.createElement("pzIndicator");
            actionTaken.appendChild(document.createTextNode(machineJobInfoRequest.getPzIndicator()));
            txmlRoot.appendChild(pzIndicator);

            // action taken element
            Element stationName = document.createElement("stationName");
            actionTaken.appendChild(document.createTextNode(machineJobInfoRequest.getStationName()));
            txmlRoot.appendChild(stationName);

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            transformer.transform(domSource, streamResult);

            System.out.println("XML File has been created ");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}