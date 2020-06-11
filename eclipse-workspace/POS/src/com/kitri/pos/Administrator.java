package com.kitri.pos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.border.LineBorder;

public class Administrator extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ForcePos forcePos;
	private JPanel contentPane; // 전체 프레임
	private JTextField userTf; // 아이디 입력
	private JTextField passTf; // 패스워드 입력
	private JTextField nameTf; // 이름 입력
	String user;
	String pass;
	String name;
	private JTextField notice; // 상태창
	private ForcePos frame; // 메인프레임
	private UserDto userDto; //Userdto 클래스 
	private UserDao userDao; //UserDao 클래스
	private JComboBox authority; //등록 권한
	private JComboBox authorityUp;// 수정 권한
	Vector<UserDto> data;
	Vector<String> userColumn;
	String auth; // 콤보박스 선택에 따라 권한 설정
	private JPanel pMonitor;
	private JTable table;
	private JTextField upuserTF;
	private JTextField upassTf;
	private JTextField unameTf;
	private boolean result; // 인서트 결과값 저장
	private DefaultTableModel tm;

// String colName[] = { "유저코드", "", "", "", "" };
//	Object data[][] = { { "1", "개나리", "주간1" }, { "2", "노오란", "야간1" }, { "3", "꽃그늘", "주간2" },
//			{ "4", "최아래", "야간2" } };

	// 회원등록 패널
	JPanel pRegister = new JPanel();
	// 회원테이블 패널
	JPanel pTable = new JPanel();
	// 회원수정 패널
	JPanel ppRegister;

	CardLayout card = new CardLayout();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {

					Administrator administrator = new Administrator();
					administrator.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//	public void showFrameTest() {
//		frame1.setVisible(true);
//		frame.dispose();
//	}

	public static void tableCellCenter(JTable table) {

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성

		dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

		TableColumnModel tcm = table.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

		// 전체 열에 지정
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
	}

	// 보여줘
	public void showFrame() {
		frame = new ForcePos();
		this.setVisible(false);
		frame.setVisible(true);
	}


	public Administrator() {

		setTitle("Force.pos");
		setFont(new Font("맑은 고딕", Font.BOLD, 20));
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 15, 1326, 753);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pStatusBar = new JPanel();
		pStatusBar.setBackground(new Color(0, 0, 128));
		pStatusBar.setBounds(0, 0, 1308, 51);
		contentPane.add(pStatusBar);
		pStatusBar.setLayout(null);

		JLabel titleLabel = new JLabel("Force. pos");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(new Color(0, 0, 128));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		titleLabel.setBounds(14, 8, 241, 31);
		pStatusBar.add(titleLabel);

		notice = new JTextField();
		notice.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		notice.setText("\uC0C1\uD488\uBA85(..)\uB294 \uC720\uD1B5\uAE30\uD55C\uC774 \uC9C0\uB0AC\uC2B5\uB2C8\uB2E4.");
		notice.setHorizontalAlignment(SwingConstants.CENTER);
		notice.setBounds(258, 8, 726, 31);
		pStatusBar.add(notice);
		notice.setColumns(10);

		JLabel dateLabel = new JLabel("2019-04-01 \uC624\uD6C4 5:01");
		dateLabel.setBackground(new Color(0, 0, 128));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(new Color(255, 255, 255));
		dateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		dateLabel.setBounds(1016, 8, 278, 31);
		pStatusBar.add(dateLabel);

		JPanel pMainBtn = new JPanel();
		pMainBtn.setBackground(new Color(255, 255, 255));
		pMainBtn.setBounds(0, 585, 1144, 120);
		contentPane.add(pMainBtn);
		pMainBtn.setLayout(null);

		JLabel idLabel = new JLabel();
		idLabel.setText("\uAD00\uB9AC\uC790");
		idLabel.setBackground(new Color(105, 105, 105));
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		idLabel.setBounds(860, 0, 201, 120);
		pMainBtn.add(idLabel);

		JButton mBtnInven = new JButton("\uC7AC\uACE0");
		mBtnInven.setBackground(new Color(28, 94, 94));
		mBtnInven.setForeground(new Color(255, 255, 255));
		mBtnInven.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnInven.setBounds(0, 0, 157, 120);
		pMainBtn.add(mBtnInven);

		JButton mBtnSale = new JButton("\uD310\uB9E4");
		mBtnSale.setBackground(new Color(99, 166, 166));
		mBtnSale.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnSale.setForeground(new Color(255, 255, 255));
		mBtnSale.setBounds(156, 0, 157, 120);
		pMainBtn.add(mBtnSale);

		JButton mBtnCalc = new JButton("\uC815\uC0B0");
		mBtnCalc.setBackground(new Color(28, 94, 94));
		mBtnCalc.setForeground(new Color(255, 255, 255));
		mBtnCalc.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnCalc.setBounds(313, 0, 157, 120);
		pMainBtn.add(mBtnCalc);

		JButton mBtnStat = new JButton("\uD1B5\uACC4");
		mBtnStat.setBackground(new Color(99, 166, 166));
		mBtnStat.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnStat.setForeground(new Color(255, 255, 255));
		mBtnStat.setBounds(470, 0, 157, 120);
		pMainBtn.add(mBtnStat);

		JButton mBtnAccount = new JButton("\uACC4\uC815");
		mBtnAccount.setBackground(new Color(28, 94, 94));
		mBtnAccount.setForeground(new Color(255, 255, 255));
		mBtnAccount.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnAccount.setBounds(626, 0, 157, 120);
		pMainBtn.add(mBtnAccount);

		JPanel pSellFunction = new JPanel();
		pSellFunction.setBackground(new Color(0, 0, 128));
		pSellFunction.setBounds(1144, 50, 164, 655);
		contentPane.add(pSellFunction);
		pSellFunction.setLayout(null);

		// 유저등록 버튼
		JButton userInsert = new JButton("\uC720\uC800\uB4F1\uB85D");
		userInsert.setForeground(new Color(255, 255, 255));
		userInsert.setBackground(new Color(0, 0, 128));
		userInsert.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		userInsert.setBounds(0, 10, 164, 120);
		pSellFunction.add(userInsert);

		// 유저수정 버튼
		JButton userUpdate = new JButton("\uC720\uC800\uC218\uC815");
		userUpdate.setBackground(new Color(100, 149, 237));
		userUpdate.setForeground(new Color(255, 255, 255));
		userUpdate.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		userUpdate.setBounds(0, 130, 164, 120);
		pSellFunction.add(userUpdate);

		// 유저삭제 버튼
		JButton userDelete = new JButton("\uC720\uC800\uC0AD\uC81C");
		userDelete.setBackground(new Color(0, 0, 128));
		userDelete.setForeground(new Color(255, 255, 255));
		userDelete.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		userDelete.setBounds(0, 260, 164, 120);
		pSellFunction.add(userDelete);

		JButton sBtnPdChange = new JButton("\uCD9C\uACB0");
		sBtnPdChange.setBackground(new Color(100, 149, 237));
		sBtnPdChange.setForeground(new Color(255, 255, 255));
		sBtnPdChange.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnPdChange.setBounds(0, 390, 164, 120);
		pSellFunction.add(sBtnPdChange);

		JButton logout = new JButton("\uB85C\uADF8\uC544\uC6C3");
		logout.setBackground(new Color(255, 69, 0));
		logout.setForeground(new Color(255, 255, 255));
		logout.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		logout.setBounds(0, 520, 164, 120);
		pSellFunction.add(logout);

		setpMonitor(new JPanel());
		getpMonitor().setSize(new Dimension(1144, 533));
		getpMonitor().setBackground(new Color(255, 255, 255));
		getpMonitor().setBounds(0, 50, 1144, 533);
		contentPane.add(getpMonitor());

		pRegister.setBackground(SystemColor.desktop);
		pRegister.setBorder(new LineBorder(new Color(0, 0, 0)));
		pRegister.setLayout(null);

		ppRegister = new JPanel();
		ppRegister.setBackground(Color.ORANGE);
		getpMonitor().add(ppRegister, "name_19549728576459");
		ppRegister.setLayout(null);

		JPanel prInput = new JPanel();
		prInput.setBorder(new LineBorder(new Color(0, 0, 0)));
		prInput.setBounds(451, 10, 356, 513);
		prInput.setLayout(new GridLayout(11, 1, 0, 0));
		pRegister.add(prInput);
//---------------------------------------------------------------------//
//		String header[] = {"유저코드", "패스워드", "아이디", "권한", "이름"};

		// 화면에 뿌려주는 테이블 !!!영역
		setUserDao(new UserDao());
		data = getUserDao().getMemberList();
//		data.clear();
//		data = userDao.getMemberList();

		// 컬럼명
		userColumn = new Vector<String>();
		
		userColumn.addElement("유저코드");
		userColumn.addElement("패스워드");
		userColumn.addElement("아이디");
		userColumn.addElement("권한");
		userColumn.addElement("이름");

		setTm(new DefaultTableModel(userColumn, 0));
		setTable(new JTable(getTm()));

		JScrollPane scrollPane = new JScrollPane(getTable());
		pTable.add(scrollPane);
		getTable().setRowHeight(60);
		tableCellCenter(getTable());
		pTable.setLayout(null);
		scrollPane.setBounds(0, 5, 1144, 528);

		int size = data.size();

		for (int i = 0; i < size; i++) {
			// 행
			Vector<String> row = new Vector<String>();

			// 숫자를 문자로 변환 행에 추가
			row.addElement(data.get(i).getUserCode() + "");
			row.addElement(data.get(i).getPw());
			row.addElement(data.get(i).getId());
			row.addElement(data.get(i).getAuthority());
			row.addElement(data.get(i).getName());

			getTm().addRow(row);

		}
		
		// 카드레이아웃담당.
		getpMonitor().setLayout(card);
		getpMonitor().add("pTable", pTable);
		getpMonitor().add("pRegister", pRegister);
		getpMonitor().add("ppRegister", ppRegister);
		card.show(getpMonitor(), "pTable");

		JPanel panel = new JPanel();
		prInput.add(panel);

		JLabel userIdLabel = new JLabel("\uC720\uC800ID");
		userIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userIdLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		userIdLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInput.add(userIdLabel);

		// 회원등록 - 유저아이디입력
		userTf = new JTextField();
		userTf.setHorizontalAlignment(SwingConstants.CENTER);
		prInput.add(userTf);
		userTf.setColumns(10);
		JLabel passWLabel_1 = new JLabel("\uD328\uC2A4\uC6CC\uB4DC");
		passWLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		passWLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInput.add(passWLabel_1);

		// 회원등록 - 패스워드입력
		passTf = new JTextField();
		passTf.setHorizontalAlignment(SwingConstants.CENTER);
		prInput.add(passTf);
		passTf.setColumns(10);
		JLabel lblNewLabel_2 = new JLabel("\uC774\uB984");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInput.add(lblNewLabel_2);

		// 회원등록 - 이름입력
		nameTf = new JTextField();
		nameTf.setHorizontalAlignment(SwingConstants.CENTER);
		prInput.add(nameTf);
		nameTf.setColumns(10);
		JLabel lblNewLabel_3 = new JLabel("\uAD8C\uD55C");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInput.add(lblNewLabel_3);

		// 권한배열

		setAuthority(new JComboBox());
		getAuthority().addItem("관리자");
		getAuthority().addItem("직원");
		prInput.add(getAuthority());

		// 아래버튼패널
		JPanel pB = new JPanel();
		prInput.add(pB);
		pB.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// 확인버튼
		JButton ok = new JButton("\uD655\uC778");
		ok.setMargin(new Insets(2, 20, 2, 20));
		ok.setHorizontalTextPosition(SwingConstants.CENTER);
		ok.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		ok.setAlignmentX(Component.CENTER_ALIGNMENT);
		pB.add(ok);

		// 취소버튼
		JButton cancel = new JButton("\uCDE8\uC18C");
		cancel.setMargin(new Insets(2, 20, 2, 20));
		cancel.setBackground(new Color(255, 99, 71));
		cancel.setHorizontalTextPosition(SwingConstants.CENTER);
		cancel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		pB.add(cancel);

		// 회원수정 - 화면
		JPanel prInsert = new JPanel();
		prInsert.setBounds(451, 10, 356, 513);
		prInsert.setBorder(new LineBorder(new Color(0, 0, 0)));
		ppRegister.add(prInsert);
		prInsert.setLayout(new GridLayout(11, 1, 0, 0));

		JPanel panel_2 = new JPanel();
		prInsert.add(panel_2);

		JLabel upuserL = new JLabel("\uC720\uC800ID");
		upuserL.setHorizontalAlignment(SwingConstants.CENTER);
		upuserL.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInsert.add(upuserL);

		upuserTF = new JTextField();
		upuserTF.setHorizontalAlignment(SwingConstants.CENTER);
		upuserTF.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		upuserTF.setEnabled(false);
		upuserTF.setDragEnabled(true);
		upuserTF.setColumns(10);
		prInsert.add(upuserTF);

		JLabel uppassL = new JLabel("\uD328\uC2A4\uC6CC\uB4DC");
		uppassL.setHorizontalAlignment(SwingConstants.CENTER);
		uppassL.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInsert.add(uppassL);

		upassTf = new JTextField();
		upassTf.setHorizontalAlignment(SwingConstants.CENTER);
		upassTf.setColumns(10);
		prInsert.add(upassTf);

		JLabel upnameL = new JLabel("\uC774\uB984");
		upnameL.setHorizontalAlignment(SwingConstants.CENTER);
		upnameL.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInsert.add(upnameL);

		unameTf = new JTextField();
		unameTf.setHorizontalAlignment(SwingConstants.CENTER);
		unameTf.setColumns(10);
		prInsert.add(unameTf);

		JLabel upauthL = new JLabel("\uAD8C\uD55C");
		upauthL.setHorizontalAlignment(SwingConstants.CENTER);
		upauthL.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInsert.add(upauthL);

		// 수정화면 콤보박스
		setAuthorityUp(new JComboBox());
		getAuthorityUp().addItem("관리자");
		getAuthorityUp().addItem("직원");
		prInsert.add(getAuthorityUp());

		JPanel panel_3 = new JPanel();
		prInsert.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton button = new JButton("\uC218\uC815");
		button.setMargin(new Insets(2, 20, 2, 20));
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		button.setAlignmentX(0.5f);
		panel_3.add(button);

		JButton button_1 = new JButton("\uCDE8\uC18C");
		button_1.setMargin(new Insets(2, 20, 2, 20));
		button_1.setHorizontalTextPosition(SwingConstants.CENTER);
		button_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button_1.setBackground(new Color(255, 99, 71));
		panel_3.add(button_1);

		// 이벤트 리스너 등록
		userInsert.addActionListener(this);
		userUpdate.addActionListener(this);
		//
		userDelete.addActionListener(this);
		logout.addActionListener(this);
		getAuthority().addActionListener(this);
		getAuthorityUp().addActionListener(this);
		ok.addActionListener(this);
		cancel.addActionListener(this);
		button.addActionListener(this);
		button_1.addActionListener(this);
		
		//
		getTable().addMouseListener(ms);
	}

	// 회원등록창에 입력된 값을 보여줘
	public UserDto getViewData() {

		userDto = new UserDto();

		userDto.setPw(passTf.getText());
		userDto.setId(userTf.getText());
		userDto.setName(nameTf.getText());
		userDto.setAuthority(auth);

		return userDto;

	}

	// 회원 수정창에 입력된 값을 보여줘
	public UserDto getViewUpdata() {

		userDto = new UserDto();

		userDto.setId(upuserTF.getText());
		userDto.setPw(upassTf.getText());
		userDto.setName(unameTf.getText());
		userDto.setAuthority(auth);

		return userDto;

	}
	
	
	public boolean isUserId() {

		user = userTf.getText().trim();
		pass = passTf.getText().trim();
		name = nameTf.getText().trim();

		if (user.length() > 10) {
			JOptionPane.showMessageDialog(this, "아이디는 10자 미만으로 생성가능합니다.", "ID생성 오류", JOptionPane.WARNING_MESSAGE);
			setResult(false);
		} else if (user.isEmpty() || pass.isEmpty() || name.isEmpty()) {
			JOptionPane.showMessageDialog(this, "공백은 안되요!!!");
				setResult(false);
		} else {
				setResult(true);
		}
		
		return isResult();
	}

	// 회원등록 유효성검사.
	void insertUser() {

		getViewData();

		if (isUserId()) {
			JOptionPane.showMessageDialog(this, "등록이 완료되었습니다. 감사합니다.");
			card.show(getpMonitor(), "pTable");
		} else {
			JOptionPane.showMessageDialog(this, "등록이 실패되었습니다.");
			return;
		}
	}

	void updateUser() {

		setUserDao(new UserDao());
		UserDto re = getViewUpdata();

		try {
			boolean result;
			
			result = getUserDao().updateMember(re);

			if (result) {
				JOptionPane.showMessageDialog(this, "수정이 완료되었습니다.");
				card.show(getpMonitor(), "pTable");
			} else {
				JOptionPane.showMessageDialog(this, "수정이 실패되었습니다.");
			 	return;
			}
			
			getUserDao().userSelectAll(getTm());
			
		} catch (SQLException e) {
			System.out.println("업데이트 실패");
			e.printStackTrace();
		}
	}

	void deleteUser() {
		// 행의 번호를 뽑아옴.
		int numberRow = getTable().getSelectedRow();
		// 열의 번호를 뽑아옴.
//		int numberColumn = table.getSelectedColumn();
		String id = (String) getTm().getValueAt(numberRow, 2);

		if (id.length() == 0) {
			JOptionPane.showMessageDialog(this, "id를 클릭해주세요.");
			return;
		}

		setUserDao(new UserDao());

		try {
			boolean result;
			result = getUserDao().deleteMember(id);

			if (result) {
				JOptionPane.showMessageDialog(this, "삭제완료");
			} else {
				JOptionPane.showMessageDialog(this, "삭제실패");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// 유저 등록 텍스트필드 초기화
	public void tfClear() {
		userTf.setText("");
		passTf.setText("");
		nameTf.setText("");
	}

	// 유저 수정 텍스트필드 초기화
	public void tfUClear() {
		upassTf.setText("");
		upuserTF.setText("");
		unameTf.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object ob = e.getActionCommand();
		Object ob2 = e.getSource();

		//콤보박스에서 권한 설정할 시 권한 변경
		if (ob2 == getAuthority()) {
			String str = getAuthority().getSelectedItem().toString();
//			System.out.println(str);
			if(str.equals("관리자")) {
				auth = "T";
			}else if(str.equals("직원")){
				auth = "F";
			}
		}
			if(ob2 == getAuthorityUp()) {
			String str = getAuthorityUp().getSelectedItem().toString();
			System.out.println(str);
				if(str.equals("관리자")) {
					auth = "T";
				}else if(str.equals("직원")){
					auth = "F";
				}	
				
				System.out.println(auth);
		}


		// 회원등록이라고하죠.
		if (ob.equals("유저등록")) {
			card.show(getpMonitor(), "pRegister");
//			isSelect(); //권한 설정
			tfClear();
		}

		// 회원수정이라고 하죠.
		if (ob.equals("유저수정")) {
			tfUClear();
			// 행의 번호를 뽑아옴.
			int numberRow = getTable().getSelectedRow();
			
			// 열의 번호를 뽑아옴.
//			int numberColumn = table.getSelectedColumn();
			if (getTable().getSelectedRow() < 0) {
				JOptionPane.showMessageDialog(this, "테이블을 클릭해주세요.");
			} else {
				String id = (String) getTm().getValueAt(numberRow, 2);
				card.show(getpMonitor(), "ppRegister");
				upuserTF.setText(id);
			}
		}

		// 유저를 지워보도록 하죠.
		if (ob.equals("유저삭제")) {

			UserDao userDao = new UserDao();

			if (getTable().getSelectedRow() < 0) {
				JOptionPane.showMessageDialog(this, "테이블을 클릭해주세요.");
			
		} else {
			int x = JOptionPane.showConfirmDialog(this, "정말 삭제 하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);
			if (x == JOptionPane.OK_OPTION) {
				deleteUser();
				try {
					userDao.userSelectAll(getTm());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "삭제를 취소하였습니다.");
				}
			}
		}


		if (ob.equals("수정")) {
			updateUser();
		}

		// 확인을 누르면 유저등록창
		if (ob.equals("확인")) {

			insertUser(); // 유효성 검사

			UserDto re = getViewData(); // 실제 넘어간 데이터 userDto에 저장.
			setUserDao(new UserDao()); // userDao 객체 생성

			if (isResult()) { // 데이터가 넘어 갔다면.
				try {
					getUserDao().getMemberList(); // select 문 실행.
					getUserDao().insertMember(re); // insert문 실행.
					getUserDao().userSelectAll(getTm());
//					tableRefresh();
				} catch (SQLException e1) {
					System.out.println("확인 실패");
					e1.printStackTrace();
				}
			} else {
				setResult(false);
				return;
			}

		}

		// 취소버튼을 누르는 동시에 다시 테이블화면으로.
		if (ob.equals("취소")) {
			System.out.println("취소버튼등록");
			card.show(getpMonitor(), "pTable");
		}

		// 말그대로 로그아웃 메인프레임으로 넘어감.
		if (ob.equals("로그아웃")) {
			this.setVisible(false);
			setForcePos(new ForcePos());
			getForcePos().setVisible(true);
		}

	}
	
	public JComboBox getAuthority() {
		return authority;
	}

	public void setAuthority(JComboBox authority) {
		this.authority = authority;
	}

	public JComboBox getAuthorityUp() {
		return authorityUp;
	}

	public void setAuthorityUp(JComboBox authorityUp) {
		this.authorityUp = authorityUp;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public DefaultTableModel getTm() {
		return tm;
	}

	public void setTm(DefaultTableModel tm) {
		this.tm = tm;
	}

	public ForcePos getForcePos() {
		return forcePos;
	}

	public void setForcePos(ForcePos forcePos) {
		this.forcePos = forcePos;
	}

	public JPanel getpMonitor() {
		return pMonitor;
	}

	public void setpMonitor(JPanel pMonitor) {
		this.pMonitor = pMonitor;
	}
	
	MouseAdapter ms = new MouseAdapter() {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			super.mouseClicked(e);
			
			setTable((JTable) e.getComponent());
			setTm((DefaultTableModel) getTable().getModel());
			
		}
		
	};
}
