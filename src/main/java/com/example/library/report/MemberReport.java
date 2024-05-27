//package com.example.library.report;
//
//import com.example.library.dao.MemberDAO;
//import com.example.library.model.Member;
//
//import java.util.List;
//
//public class MemberReport {
//
//    private MemberDAO memberDAO;
//
//    public MemberReport() {
//        this.memberDAO = new MemberDAO();
//    }
//
//    /**
//     * Tạo và trả về báo cáo danh sách thành viên.
//     *
//     * @return Chuỗi báo cáo danh sách thành viên.
//     */
//    public String generateMemberListReport() {
//        List<Member> memberList = memberDAO.getAllEmployees();
//        StringBuilder reportBuilder = new StringBuilder();
//        reportBuilder.append("------ Member List Report ------\n");
//        for (Member member : memberList) {
//            reportBuilder.append("Member ID: ").append(member.getId()).append("\n");
//            reportBuilder.append("Name: ").append(member.getName()).append("\n");
//            reportBuilder.append("Position: ").append(member.getPosition()).append("\n");
//            reportBuilder.append("Contact: ").append(member.getContact()).append("\n");
//            reportBuilder.append("-------------------------------\n");
//        }
//        return reportBuilder.toString();
//    }
//}
