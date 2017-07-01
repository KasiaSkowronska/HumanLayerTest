package pl.nowy.Elements;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import pl.nowy.HumanElements.Dictionary;
import pl.nowy.HumanElements.Entry;
import pl.nowy.Temporary.EntryOld;
import pl.nowy.Temporary.TemporaryEntryFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Kasia on 09.06.2017.
 */
public class MainWindow extends VerticalLayout {

    private TopPanel topPanel;
    private HorizontalLayout mainPanel;
    private IndexPanel indexPanel;
    private EntryWindowFactory factory = new EntryWindowFactory();
    private VerticalLayout entryWindow;
    private TemporaryEntryFactory tempFactory = new TemporaryEntryFactory();

    public MainWindow() {
        setSizeFull();
        setMargin(new MarginInfo(false, true, true, true));
        topPanel = new TopPanel();
        topPanel.setSizeFull();

        mainPanel = new HorizontalLayout();
        mainPanel.addStyleName("main-window");
        mainPanel.setSizeFull();

        //setComponentAlignment(mainPanel, Alignment.TOP_CENTER);



        EntryOld entryOld = tempFactory.createEntry();
        entryWindow = factory.createEntryWindow(entryOld);
        entryWindow.setSizeFull();


        indexPanel = new IndexPanel();
        indexPanel.setSizeFull();

        mainPanel.addComponent(entryWindow);
        mainPanel.addComponent(indexPanel);

        addComponent(topPanel);
        addComponent(mainPanel);

        setComponentAlignment(mainPanel, Alignment.TOP_CENTER);
        setComponentAlignment(topPanel, Alignment.TOP_CENTER);

        topPanel.getAboutButton().addClickListener(event -> {
            try {
                testJAXB();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });

    }

    public void testJAXB() throws JAXBException {

        JAXBContext ctx = JAXBContext.newInstance(Dictionary.class);
        Unmarshaller unmarshaller = ctx.createUnmarshaller();


        Dictionary dic = (Dictionary) unmarshaller.unmarshal(new File("/Users/Kasia/git/nowy/src/main/resources/test.xml"));
        for (Entry entry : dic.getEntries()){
            System.out.println(entry);
            System.out.println(entry.getHumanLayer());
        }
    }




}