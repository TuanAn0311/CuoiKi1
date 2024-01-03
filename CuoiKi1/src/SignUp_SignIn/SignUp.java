package SignUp_SignIn;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.ConnectJDBC;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SignUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUN;
	private JTextField txtEmail;
	private JTextField txtPN;
	private JPasswordField pass;
	private JPasswordField repass;
	private JLabel note;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton bk = new JButton("");
		bk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showOptionDialog(null, "Bạn muốn tiếp tục?", "Xác nhận",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

				if (result == JOptionPane.YES_OPTION) {
					SignIn s = new SignIn();
					s.setVisible(true);
					setVisible(false);
				}
			}
		});
		bk.setBounds(0, 0, 49, 38);
		contentPane.add(bk);
		bk.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SignIn.class.getResource("iconback.png"))));

		JLabel xc = new JLabel("Xin Chào !");
		xc.setFont(new Font("Tw Cen MT", Font.BOLD, 50));
		xc.setBounds(122, 10, 233, 70);
		contentPane.add(xc);

		JLabel ttk = new JLabel("Tạo Tài Khoản Mới");
		ttk.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ttk.setBounds(114, 86, 251, 38);
		contentPane.add(ttk);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUsername.setBounds(10, 293, 121, 38);
		contentPane.add(lblUsername);

		txtUN = new JTextField();
		txtUN.setBounds(178, 298, 233, 30);
		contentPane.add(txtUN);
		txtUN.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPassword.setBounds(10, 363, 121, 38);
		contentPane.add(lblPassword);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEmail.setBounds(10, 157, 121, 38);
		contentPane.add(lblEmail);

		JLabel lblPhonenumber = new JLabel("PhoneNumber:");
		lblPhonenumber.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPhonenumber.setBounds(10, 223, 171, 38);
		contentPane.add(lblPhonenumber);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(178, 162, 233, 30);
		contentPane.add(txtEmail);

		txtPN = new JTextField();
		txtPN.setColumns(10);
		txtPN.setBounds(178, 228, 233, 30);
		contentPane.add(txtPN);

		pass = new JPasswordField();
		pass.setBounds(178, 371, 233, 30);
		contentPane.add(pass);

		repass = new JPasswordField();
		repass.setBounds(178, 431, 233, 30);
		contentPane.add(repass);

		JLabel lblRepassword = new JLabel("Re-Password:");
		lblRepassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRepassword.setBounds(10, 423, 158, 38);
		contentPane.add(lblRepassword);

		JButton btnNewButton = new JButton("Tiếp Tục");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnNewButton.setBounds(149, 531, 165, 47);
		contentPane.add(btnNewButton);

		note = new JLabel("");
		note.setForeground(new Color(255, 0, 51));
		note.setFont(new Font("Tahoma", Font.ITALIC, 15));
		note.setBounds(147, 471, 301, 30);
		contentPane.add(note);

		JLabel bg = new JLabel("");
		bg.setBounds(0, 0, 474, 603);
		contentPane.add(bg);
		bg.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(SignIn.class.getResource("backgroundMK.jpg"))));

		setLocationRelativeTo(null);
	}

	public void SignUp() {
		ConnectJDBC jdbc = new ConnectJDBC();
		Connection conn = jdbc.getConnection();

		String sql = "INSERT INTO Users VALUES (?, ?, ?, ?)";
		if (txtUN.getText().equals("") || pass.getText().equals("") || txtPN.getText().equals("")
				|| txtEmail.getText().equals("")) {
			note.setText("Thiếu Thông tin!");
			JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin! ");
			
		} else {
			try {
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setString(1, txtUN.getText());
				pstm.setString(2, pass.getText());
				pstm.setString(3, txtEmail.getText());
				pstm.setString(4, txtPN.getText());
				if (!repass.getText().equals(pass.getText())) {
					JOptionPane.showMessageDialog(null, "Nhập lại mật khẩu chưa đúng!");
					
				} else {
					int rowsAffected = pstm.executeUpdate(); // Thực hiện thêm dữ liệu vào cơ sở dữ liệu

					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(null, "Tạo Tài Khoản Thành Công!");
						SignIn s = new SignIn();
						s.setVisible(true);
						
					} else {
						JOptionPane.showMessageDialog(null, "Tạo Tài Khoản Thất Bại!");
					}
				}

				pstm.close(); // Đóng PreparedStatement
				conn.close(); // Đóng Connection
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Tạo Tài Khoản Thất Bại!");
			}
		}

	}
}
