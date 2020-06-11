package com.kitri.pos.Stock;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame implements ActionListener{

	StockPopupIn stockpopupin = new StockPopupIn();
	StockPopupChg stockpopupchg = new StockPopupChg();
	StockPopupSearch stockpopupsearch = new StockPopupSearch();
	StockBtn stockbtn;
	StockMonitor stockmonitor;
	CardLayout monitor;
	CardLayout btn;
	
	private JPanel contentPane;
	private JTextField notice;
	private JPanel pStatusBar;
	private JLabel titleLabel;
	private JLabel dateLabel;
	private JPanel pMainBtn;
	private JLabel idLabel;
	private JButton mBtnInven;
	private JButton mBtnSale;
	private JButton mBtnCalc;
	private JButton mBtnStat;
	private JButton mBtnAccount;
	private JPanel pMonitor;
	private JPanel pFBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		monitor = new CardLayout();
		stockmonitor = new StockMonitor();
		stockbtn = new StockBtn();
		btn = new CardLayout();
		
		setFont(new Font("���� ���", Font.BOLD, 20));
		setTitle("Force.pos");
//		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1326, 753);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pStatusBar = new JPanel();
		pStatusBar.setBackground(new Color(0, 0, 128));
		pStatusBar.setBounds(0, 0, 1320, 51);
		contentPane.add(pStatusBar);
		pStatusBar.setLayout(null);
		
		titleLabel = new JLabel("Force. pos");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(new Color(0, 0, 128));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("���� ���", Font.BOLD, 25));
		titleLabel.setBounds(14, 8, 241, 31);
		pStatusBar.add(titleLabel);
		
		notice = new JTextField();
		notice.setFont(new Font("���� ���", Font.PLAIN, 20));
		notice.setText("\uC0C1\uD488\uBA85(..)\uB294 \uC720\uD1B5\uAE30\uD55C\uC774 \uC9C0\uB0AC\uC2B5\uB2C8\uB2E4.");
		notice.setHorizontalAlignment(SwingConstants.CENTER);
		notice.setBounds(258, 8, 726, 31);
		pStatusBar.add(notice);
		notice.setColumns(10);
		
		dateLabel = new JLabel("2019-06-11 \uC624\uD6C4 5:01");
		dateLabel.setBackground(new Color(0, 0, 128));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(new Color(255, 255, 255));
		dateLabel.setFont(new Font("���� ���", Font.PLAIN, 20));
		dateLabel.setBounds(1016, 8, 278, 31);
		pStatusBar.add(dateLabel);
		
		pMainBtn = new JPanel();
		pMainBtn.setBackground(new Color(255, 255, 255));
		pMainBtn.setBounds(0, 600, 1157, 125);
		contentPane.add(pMainBtn);
		pMainBtn.setLayout(null);
		
		idLabel = new JLabel("\uAD00\uB9AC\uC790");
		idLabel.setForeground(Color.BLACK);
		idLabel.setBackground(new java.awt.Color(105, 105, 105));
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("���� ���", Font.BOLD, 20));
		idLabel.setBounds(860, 0, 201, 125);
		idLabel.setOpaque(true);
		pMainBtn.add(idLabel);
		
		mBtnInven = new JButton("\uC7AC\uACE0");
		mBtnInven.setBackground(new Color(28, 94, 94));
		mBtnInven.setForeground(new Color(255, 255, 255));
		mBtnInven.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnInven.setBounds(0, 0, 157, 125);
		pMainBtn.add(mBtnInven);
		
		mBtnSale = new JButton("\uD310\uB9E4");
		mBtnSale.setBackground(new Color(99, 166, 166));
		mBtnSale.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnSale.setForeground(new Color(255, 255, 255));
		mBtnSale.setBounds(156, 0, 157, 125);
		pMainBtn.add(mBtnSale);
		
		mBtnCalc = new JButton("\uC815\uC0B0");
		mBtnCalc.setBackground(new Color(28, 94, 94));
		mBtnCalc.setForeground(new Color(255, 255, 255));
		mBtnCalc.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnCalc.setBounds(313, 0, 157, 125);
		pMainBtn.add(mBtnCalc);
		
		mBtnStat = new JButton("\uD1B5\uACC4");
		mBtnStat.setBackground(new Color(99, 166, 166));
		mBtnStat.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnStat.setForeground(new Color(255, 255, 255));
		mBtnStat.setBounds(470, 0, 157, 125);
		pMainBtn.add(mBtnStat);
		
		mBtnAccount = new JButton("\uACC4\uC815");
		mBtnAccount.setBackground(new Color(28, 94, 94));
		mBtnAccount.setForeground(new Color(255, 255, 255));
		mBtnAccount.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnAccount.setBounds(626, 0, 157, 125);
		pMainBtn.add(mBtnAccount);
		
		pMonitor = new JPanel();
		pMonitor.setBackground(new Color(255, 255, 255));
		pMonitor.setBounds(0, 50, 1157, 552);
		contentPane.add(pMonitor);
		pMonitor.setLayout(monitor);
//		������гο� ī�巹�̾ƿ��ְ� ���ȭ�� �г� ��ü�����Ͽ� �߰�
		pMonitor.add(stockmonitor, "Stock");
		
		
		pFBtn = new JPanel();
		pFBtn.setBackground(Color.BLUE);
		pFBtn.setBounds(1156, 50, 164, 675);
		contentPane.add(pFBtn);
		pFBtn.setLayout(btn);
//		��ɹ�ư�г� ī�� ����ɹ�ư �г� ��ü������ �߰�
		pFBtn.add(stockbtn, "Stockbtn");
		
		
		mBtnInven.addActionListener(this);
		mBtnSale.addActionListener(this);
		mBtnCalc.addActionListener(this);
		mBtnStat.addActionListener(this);
		mBtnAccount.addActionListener(this);
		
		stockbtn.stockSearch.addActionListener(this);
		stockbtn.stockIn.addActionListener(this);
		stockbtn.stockChg.addActionListener(this);
		//stockbtn.stockRefresh.addActionListener(this);
		
		
	}//���������Ӹ޼ҵ� ��

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob==mBtnInven) {
			monitor.show(pMonitor, "Stock");
			btn.show(pFBtn, "Stockbtn");
		} else if(ob==stockbtn.stockSearch) {
			stockpopupsearch.setVisible(true);
		} else if(ob==stockbtn.stockChg) {
//			String tmp = stockmonitor.getText();
//			if (to.isEmpty()) {
//				JOptionPane.showMessageDialog(chat, "����ڸ� �����ϼ���.", "����ڿ���", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//
//			if (to.equals(myid)) {
//				JOptionPane.showMessageDialog(chat, "�ڽſ��� ������???", "����ڿ���", JOptionPane.INFORMATION_MESSAGE);
//				paper.letter.setText("");
//				return;
//			}
			
			stockpopupchg.setVisible(true);
		} else if(ob==stockbtn.stockIn) {
			stockpopupin.setVisible(true);
		} 
		/*
		else if(ob==stockbtn.stockRefresh) {
			JOptionPane.showMessageDialog(this, "����ִ� ��� ���� �Ͻðڽ��ϱ�?", "���ΰ�ħ", JOptionPane.OK_CANCEL_OPTION);
			
		}*/
		
	}//�׼�������
	
	// <���̺� ���� ��� ����> �޼ҵ�
		public static void tableCellCenter(JTable t) {
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // ����Ʈ���̺��������� ����
			dtcr.setHorizontalAlignment(SwingConstants.CENTER); // �������� ���������� CENTER��

			TableColumnModel tcm = t.getColumnModel(); // ������ ���̺��� �÷����� ������

			// ��ü ���� ����
			for (int i = 0; i < tcm.getColumnCount(); i++) {
				tcm.getColumn(i).setCellRenderer(dtcr);
			}
		}
}//Ŭ���� ��
