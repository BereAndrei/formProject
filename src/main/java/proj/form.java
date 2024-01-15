package proj;

// important import statements
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import static javax.swing.BoxLayout.Y_AXIS;



// creating a class ScrollPaneDemo1  
// for extending the JFrame  
public class form extends JFrame
{
    ArrayList<person> people = new ArrayList<>();
    JScrollPane scrlpane;
    public form()
    {

        super("Formular persoana");
        setSize(1080, 820);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        inner();
        setVisible(true);
    }
    public void inner()
    {
        ObjectMapper mapper = new ObjectMapper();
        if(new File("person.json").length()!=0)
            try {

                person[] example = mapper.readValue(new File("person.json"), person[].class);

                people = new ArrayList<>(Arrays.asList(example));


            } catch (StreamReadException e) {
                throw new RuntimeException(e);
            } catch (DatabindException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        person personimput = new person();
        String col[] = {"Nume", "Prenume", "Varsta", "Angajat", "Prezent fizic", "Studii"};
        JPanel panel = new JPanel();
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        JTable tab = new JTable(tableModel);

        //splits the window in half, one for the form one for the data to be displayed
        JPanel halfSplit = new JPanel();
        halfSplit.setLayout(new GridLayout(1, 2));
        JPanel formular = new JPanel();
        formular.setBorder(new EmptyBorder(10,10,10,10));

        //form entries
        formular.setLayout(new BoxLayout(formular, Y_AXIS));

        //person nume
        formular.add(new JLabel("Nume:"));
        JTextField formNume = new JTextField("");
        formNume.setAlignmentX(Component.CENTER_ALIGNMENT);
        formular.add(formNume);
        formNume.setMaximumSize(new Dimension(10000, 200));

        //person prenume
        formular.add(new JLabel("Prenume:"));
        JTextField formPreNume = new JTextField("");
        formPreNume.setAlignmentX(Component.CENTER_ALIGNMENT);
        formular.add(formPreNume);
        formPreNume.setMaximumSize(new Dimension(10000, 200));


        //emoployee age
        formular.add(new JLabel("Varsta"));
        JSpinner formVarsta = new JSpinner(new SpinnerNumberModel(20,0,120,1));
        formular.add(formVarsta);
        formVarsta.setMaximumSize(new Dimension(10000, 200));




        //is here
        formular.add(new JLabel("Prezent fizic"));
        JCheckBox chec = new JCheckBox("Da");
        formular.add(chec);


        //employed status
        ButtonGroup yesNo = new ButtonGroup();
        JRadioButton formDa = new JRadioButton("Da");
        JRadioButton formNu = new JRadioButton("Nu", true);
        yesNo.add(formDa);
        yesNo.add(formNu);
        JPanel barDaNu = new JPanel();
        barDaNu.add(new JLabel("Angajat:"));
        barDaNu.setMaximumSize(new Dimension(10000, 200));

        barDaNu.add(formDa);
        barDaNu.add(formNu);
        formular.add(barDaNu);

        //study
        formular.add(new JLabel("Nivel de studiu"));
        JComboBox studii = new JComboBox(person.studii);
        studii.setMaximumSize(new Dimension(10000, 200));
        formular.add(studii);




        //save
        JPanel btnPannel = new JPanel();

        JButton jbtSave = new JButton("Save");
        btnPannel.add(jbtSave);

        jbtSave.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            personimput.setNume(formNume.getText());
            personimput.setPrenume(formPreNume.getText());
            personimput.setVarsta((int) formVarsta.getValue());

            if(formNu.isSelected())
                personimput.setAngajat("Nu");
            else
                personimput.setAngajat("Da");
            if(chec.isSelected())
                personimput.setEsteAici(true);
            else
                personimput.setEsteAici(false);


            personimput.setStudiu((String) studii.getSelectedItem());
            if(Objects.equals(formNume.getText(), "") || Objects.equals(formPreNume.getText(), "")) {
                System.out.println("EMPTYYYY");
            }
            else{

                people.add(new person(formNume.getText(),formPreNume.getText(),(int) formVarsta.getValue(),personimput.getAngajat(),chec.isSelected(),(String) studii.getSelectedItem()));

                try {
                    String jsonString = mapper.writeValueAsString(people);
                    FileWriter file = new FileWriter("person.json");
                    file.write(jsonString);
                    file.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


                tableModel.addRow(personimput.personrow());


                panel.add(tab);

            }

                halfSplit.revalidate();
                halfSplit.repaint();


        }
    });

        //close
        JButton jbtClose = new JButton("Close");
        btnPannel.add(jbtClose);
        jbtClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                System.exit(0);
            }
        });
        formular.add(btnPannel);



        //partea dreapta

        panel.setLayout(new BoxLayout(panel, Y_AXIS));

        tableModel.addRow(col);

        people.forEach(e ->{
            tableModel.addRow(e.personrow());
        });


        panel.add(tab);












        scrlpane = new JScrollPane(panel);
        halfSplit.add(formular);
        halfSplit.add(scrlpane, BorderLayout.CENTER);

        getContentPane().add(halfSplit);
        System.out.println(getContentPane());


    }

    // main method
    public static void main(String[] args)
    {


        new form();
    }
}  