package model.Dao.memberDao;

public class memberDao extends SuperDao_M {

	private static memberDao mdao = new memberDao(); // 싱글톤 설정

	public static memberDao getInstance() {
		return mdao;
	} // 싱글톤 접근자 생성

	//[10-26 ] 김원종 로그인 매소드 구현
	public int login(String mid, String mpassword) { // 매개변수로 id 와 password 받기
		String sql = "select*from user where user_email=?"; // user테이블에 email 일치하는값 찾기

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid); // 아이디값 셋팅
			rs = ps.executeQuery();
			if (rs.next()) {//아이디가 만약 존재하면 
				sql = "select*from user where user_email=? and user_pw=?"; //아이디와 비밀번호가 같은거
				ps=con.prepareStatement(sql);	
				ps.setString(1, rs.getString(4)); // 아이디필드에 처음 셋팅한 값을 대입
				ps.setString(2, mpassword);// 2번째 물음표에 password대입
				rs = ps.executeQuery();
				if (rs.next()) {//만약 둘다 일치하면 
					return 1; // 일치하는 아이디가 있다
				} // 2번 if end
				return 2; // PW가 틀렸다
			} // 1번 if end

		} // try end
		catch (Exception e) {System.out.println("로그인메소드 오류" + e);return 3;} // DB오류다
		return 0;//아이디가 없다.
	}// 메소드 end






}
