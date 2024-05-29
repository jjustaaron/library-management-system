package com.example.library.ui;

import com.example.library.dao.MemberDAO;
import com.example.library.model.Member;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MemberPanel extends JPanel {
    private JTable memberTable;
    private DefaultTableModel memberTableModel;
    private MemberDAO memberDAO;

    public MemberPanel() {
        memberDAO = new MemberDAO();
        setLayout(new BorderLayout());

        memberTableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Position", "Contact"}, 0);
        memberTable = new JTable(memberTableModel);
        loadMembers();
        add(new JScrollPane(memberTable), BorderLayout.CENTER);

        JPanel memberButtonPanel = new JPanel();
        JButton addMemberButton = new JButton("Add Member");
        addMemberButton.addActionListener(this::addMember);
        JButton editMemberButton = new JButton("Edit Member");
        editMemberButton.addActionListener(this::editMember);
        JButton deleteMemberButton = new JButton("Delete Member");
        deleteMemberButton.addActionListener(this::deleteMember);
        memberButtonPanel.add(addMemberButton);
        memberButtonPanel.add(editMemberButton);
        memberButtonPanel.add(deleteMemberButton);
        add(memberButtonPanel, BorderLayout.SOUTH);
    }

    private void loadMembers() {
        List<Member> members = memberDAO.getAllEmployees();
        for (Member member : members) {
            memberTableModel.addRow(new Object[]{member.getId(), member.getName(), member.getPosition(), member.getContact()});
        }
    }

    private void addMember(ActionEvent e) {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField positionField = new JTextField();
        JTextField contactField = new JTextField();

        Object[] fields = {
                "ID:", idField,
                "Name:", nameField,
                "Position:", positionField,
                "Contact:", contactField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add Member", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String position = positionField.getText();
            String contact = contactField.getText();

            Member member = new Member(id, name, position, contact);
            memberDAO.addEmployee(member);
            memberTableModel.addRow(new Object[]{id, name, position, contact});
        }
    }

    private void editMember(ActionEvent e) {
        int selectedRow = memberTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) memberTableModel.getValueAt(selectedRow, 0);
            String name = (String) memberTableModel.getValueAt(selectedRow, 1);
            String position = (String) memberTableModel.getValueAt(selectedRow, 2);
            String contact = (String) memberTableModel.getValueAt(selectedRow, 3);

            JTextField nameField = new JTextField(name);
            JTextField positionField = new JTextField(position);
            JTextField contactField = new JTextField(contact);

            Object[] fields = {
                    "Name:", nameField,
                    "Position:", positionField,
                    "Contact:", contactField
            };

            int option = JOptionPane.showConfirmDialog(this, fields, "Edit Member", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                name = nameField.getText();
                position = positionField.getText();
                contact = contactField.getText();

                Member member = new Member(id, name, position, contact);
                memberDAO.updateEmployee(member);
                memberTableModel.setValueAt(name, selectedRow, 1);
                memberTableModel.setValueAt(position, selectedRow, 2);
                memberTableModel.setValueAt(contact, selectedRow, 3);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a member to edit.");
        }
    }

    private void deleteMember(ActionEvent e) {
        int selectedRow = memberTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) memberTableModel.getValueAt(selectedRow, 0);
            memberDAO.deleteEmployee(id);
            memberTableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a member to delete.");
        }
    }
}
