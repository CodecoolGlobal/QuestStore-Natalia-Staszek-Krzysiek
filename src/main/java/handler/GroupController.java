package handler;

import dao.GroupDAO;
import dao.StudentDetailsDAO;
import dao.UserClassDAO;
import dao.UserDAO;
import model.StudentDetails;
import model.User;
import view.GroupView;
import view.MentorView;

import java.util.ArrayList;
import java.util.List;

public class GroupController {

    private GroupView creepView;
    private MentorView mentorView;
    private GroupDAO groupDAO;
    private UserDAO userDAO;
    private UserClassDAO userClassDAO;
    private dao.StudentDetailsDAO studentDetailsDAO;

    public GroupController(GroupView creepView, MentorView mentorView, GroupDAO groupDAO, UserDAO userDAO,
                           dao.UserClassDAO userClassDAO, StudentDetailsDAO studentDetailsDAO) {
        this.creepView = creepView;
        this.mentorView = mentorView;
        this.groupDAO = groupDAO;
        this.userDAO = userDAO;
        this.userClassDAO = userClassDAO;
        this.studentDetailsDAO = studentDetailsDAO;
    }

    void createGroup() {

        String name = creepView.getTeamNameInput();
        model.Group aGroup = new model.Group(name);
        if (groupDAO.add(aGroup)) {
            creepView.displayGroupAdded();
        } else {
            creepView.displayGroupWithThisNameAlreadyExists();
        }
    }

    void assignMentorToGroup() {
        List<User> mentors = new ArrayList<>(userDAO.getAllByRole(2));
        creepView.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            creepView.pressAnyKeyToContinue();
            return;
        }
        String mentorLogin = mentorView.getMentorLoginToAssignGroup();
        if (userDAO.getByLoginAndRole(mentorLogin, 2) != null) {
            choseGroupAndAssignToMentor(mentorLogin);
        } else {
            mentorView.displayThereIsNoMentorWithThisLogin();
        }
    }

    private void choseGroupAndAssignToMentor(String mentorLogin) {
        List<model.Group> groups = new ArrayList<>(groupDAO.getAll());
        creepView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            creepView.pressAnyKeyToContinue();
            return;
        }
        String groupName = creepView.getTeamNameInput();
        if (groupDAO.getByName(groupName) != null) {
            model.Group aGroup = groupDAO.getByName(groupName);
            User mentor = userDAO.getByLogin(mentorLogin);
            boolean isAdded = userClassDAO.add(aGroup.getId(), mentor.getId());
            if (isAdded) {
                creepView.displayGroupConnectionAdded();
            } else {
                creepView.displayErrorAddingGroupConnection();
            }
        } else {
            creepView.displayThereIsNoGroupWithThisName();
        }
    }

    void revokeMentorFromGroup() {
        List<User> mentors = new ArrayList<>(userDAO.getAllByRole(2));
        creepView.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            creepView.pressAnyKeyToContinue();
            return;
        }
        String mentorLogin = mentorView.getMentorLoginToRevokeFromGroup();
        if (userDAO.getByLoginAndRole(mentorLogin, 2) != null) {
            choseGroupAndRevokeMentor(mentorLogin);
        } else {
            mentorView.displayThereIsNoMentorWithThisLogin();
        }
    }

    private void choseGroupAndRevokeMentor(String mentorLogin) {
        List<model.Group> groups = new ArrayList<>(groupDAO.getAll());
        creepView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            creepView.pressAnyKeyToContinue();
            return;
        }
        String groupName = creepView.getTeamNameInput();
        if (groupDAO.getByName(groupName) != null) {
            model.Group aGroup = groupDAO.getByName(groupName);
            User mentor = userDAO.getByLogin(mentorLogin);
            boolean isRemoved = userClassDAO.delete(aGroup.getId(), mentor.getId());
            if (isRemoved) {
                creepView.displayGroupConnectionRemoved();
            } else {
                creepView.displayErrorRemovingGroupConnection();
            }
        } else {
            creepView.displayThereIsNoGroupWithThisName();
        }
    }

    void deleteGroup() {
        List<model.Group> groups = new ArrayList<>(groupDAO.getAll());
        creepView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            creepView.pressAnyKeyToContinue();
            return;
        }
        String groupName = creepView.getTeamNameInput();
        model.Group aGroup = groupDAO.getByName(groupName);
        if (aGroup != null) {
            groupDAO.delete(aGroup);
            creepView.displayGroupDeleted();
        } else {
            creepView.displayThereIsNoGroupWithThisName();
        }
    }

    void showMentorGroups(int mentorID) {
        List<String> groupsNames = new ArrayList<>(groupDAO.getGroupsNamesByMentorId(mentorID));
        if (!groupsNames.isEmpty()) {
            for (String groupName : groupsNames) {
                creepView.displayGroupName(groupName);
                model.Group aGroup = groupDAO.getByName(groupName);
                if (!userDAO.getStudentsByGroupId(aGroup.getId()).isEmpty()) {
                    List<User> students = new ArrayList<>(userDAO.getStudentsByGroupId(aGroup.getId()));
                    creepView.displayEntriesNoInput(students);
                } else {
                    creepView.displayThisGroupHasNoStudentsAssigned();
                }
            }
        } else {
            mentorView.displayMentorHasNoGroupAssigned();
        }
        mentorView.pressAnyKeyToContinue();
    }

    void addStudentToGroup() {

        List<User> students = new ArrayList<>(userDAO.getAllByRole(3));
        mentorView.displayEntriesNoInput(students);
        if (students.isEmpty()) {
            mentorView.pressAnyKeyToContinue();
            return;
        }
        String studentLogin = mentorView.getStudentLoginToAssignToGroup();
        if (userDAO.getByLoginAndRole(studentLogin, 3) != null) {
            choseGroupAndAssignToStudent(studentLogin);
        } else {
            mentorView.studentLoginNotExist();
        }
    }

    private void choseGroupAndAssignToStudent(String studentLogin) {

        List<model.Group> groups = new ArrayList<>(groupDAO.getAll());
        mentorView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            mentorView.pressAnyKeyToContinue();
            return;
        }
        String groupName = mentorView.getGroupNameInput();
        if (groupDAO.getByName(groupName) != null) {
            model.Group aGroup = groupDAO.getByName(groupName);
            User student = userDAO.getByLogin(studentLogin);
            StudentDetails studentDetails = studentDetailsDAO.getStudentDataByStudentId(student.getId());
            studentDetails.setGroupId(aGroup.getId());
            boolean isUpdated = studentDetailsDAO.updateStudentData(studentDetails);
            if (isUpdated) {
                mentorView.displayGroupUpdated();
            } else {
                mentorView.displayErrorUpdatingGroup();
            }
        } else {
            mentorView.displayThereIsNoGroupWithThisName();
        }
    }
}

