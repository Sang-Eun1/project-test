package pse.finaltest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class JhchiFinalFrame extends JFrame implements ActionListener{
	Container frame = getContentPane();
	SubjectManager manager;


	JPanel northPanel;
	JLabel label;
	String[] subjectNum;
	JTable table;
	String s = "CA0407";




	JComboBox<String> comboBox;


	Boolean flag =false;
	DefaultTableModel model;
	String [] header = {"학번", "이름"};
	String [] header3 = {"과목번호", "과목명", "신청인원"};


	
	
	public JhchiFinalFrame(String title, SubjectManager manager){
		super(title);
		this.manager = manager;
		subjectNum = new String[manager.sublist.size()];
		this.setSize(500,250);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.setVisible(true);
	}





	public void init() {

		initNorthPanel();
		initTable();
		initMenu();

				
	}

	private void initMenu() {
		List<Subject> list = null;

		if(manager != null){
			if(flag){
				list = manager.sublist.stream()
						.sorted((o1, o2) -> o1.stdset.size() - o2.stdset.size())
						.toList();

			}
			else{
				list = manager.sublist.stream()
						.sorted((o1, o2) -> o1.stdset.size() - o2.stdset.size() * -1)
						.toList();

			}

			for (Subject subject : list) {

				if(model != null){
					this.model.addRow(new String[] {subject.subID,subject.subName,
							Integer.toString(subject.stdset.size())});
				}

			}
		}

	}

	private void initTable() {

		this.model = new DefaultTableModel(header, 0);
		this.table = new JTable(model);
		frame.add(new JScrollPane(table), BorderLayout.CENTER);

	}


	private void initNorthPanel() {

		this.northPanel = new JPanel();
		frame.setLayout(new FlowLayout());


		for (int i = 0; i < subjectNum.length; i++) {
			subjectNum[i] = manager.sublist.get(i).subID;
		}

		comboBox = new JComboBox<>(subjectNum);
		//comboBox.setSelectedIndex(1);
		comboBox.addActionListener(this);


		this.northPanel.add(comboBox);






		this.label = new JLabel("검색할 학번");

		this.northPanel.add(label);

		JTextField textField = new JTextField(10);
		textField.addActionListener(e -> {
			removeable2();
			List<Subject> list = manager.findStudent(textField.getText());
			if(list == null){
				JOptionPane.showMessageDialog(JhchiFinalFrame.this,  "검색한 학번이 없습니다.");
			}
			else{
				for (Subject subject : list) {
					this.model.addRow(new String[] {subject.subID,subject.subName,
							Integer.toString(subject.stdset.size())});
				}

			}


			textField.setText("");
		});




		this.northPanel.add(textField);

		JRadioButton asc = new JRadioButton("asc");
		asc.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					flag = true;
					removeable2();
					initMenu();

				}
			}
		});
		asc.setSelected(true);


		JRadioButton desc = new JRadioButton("desc");
		desc.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					flag = false;
					removeable2();
					initMenu();
				}
			}
		});

		this.northPanel.add(asc);
		this.northPanel.add(desc);

		ButtonGroup group = new ButtonGroup();
		group.add(asc);
		group.add(desc);

		frame.add(northPanel, BorderLayout.NORTH);




	}


	public void removeable(){
		if(model != null && model.getRowCount() >0){
			model = new DefaultTableModel(header, 0);
			table.setModel(model);
		}

	}
	public void removeable2(){
		if(model != null && model.getRowCount() >0){
			model = new DefaultTableModel(header3, 0);
			table.setModel(model);
		}

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == comboBox){
			int index = comboBox.getSelectedIndex();
			s = subjectNum[index];

		}

		removeable();
		setStudent();



	}

	private void setStudent() {
		List<Subject> list = new ArrayList<>(manager.sublist);
		for (Subject subject : list) {
			if(subject.subID.equals(s)){
				for(Student student : subject.stdset)
					this.model.addRow(new String[] {student.stdID ,student.stdName});
			}

		}

	}
}
