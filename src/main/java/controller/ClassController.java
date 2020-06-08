package controller;

import dao.ClassDAO;
import dao.StudentDetailsDAO;
import dao.UserClassDAO;
import dao.UserDAO;
import model.Class;
import model.StudentDetails;
import model.User;
import view.ClassView;
import view.MentorView;

import java.util.ArrayList;
import java.util.List;

public class ClassController {

    private ClassView classView;
    private MentorView mentorView;
    private ClassDAO classDAO;
    private UserDAO userDAO;
    private UserClassDAO userClassDAO;
    private dao.StudentDetailsDAO studentDetailsDAO;

    public ClassController(ClassView classView, MentorView mentorView, dao.ClassDAO classDAO, UserDAO userDAO,
                           dao.UserClassDAO userClassDAO, StudentDetailsDAO studentDetailsDAO) {
        this.classView = classView;
        this.mentorView = mentorView;
        this.classDAO = classDAO;
        this.userDAO = userDAO;
        this.userClassDAO = userClassDAO;
        this.studentDetailsDAO = studentDetailsDAO;
    }

    void createGroup() {

        String name = classView.getTeamNameInput();
        Class aClass = new Class(name);
        if (classDAO.add(aClass)) {
            classView.displayGroupAdded();
        } else {
            classView.displayGroupWithThisNameAlreadyExists();
        }
    }

    void assignMentorToGroup() {
        List<User> mentors = new ArrayList<>(userDAO.getAllByRole(2));
        classView.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            classView.pressAnyKeyToContinue();
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
        List<Class> groups = new ArrayList<>(classDAO.getAll());
        classView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            classView.pressAnyKeyToContinue();
            return;
        }
        String groupName = classView.getTeamNameInput();
        if (classDAO.getByName(groupName) != null) {
            Class aClass = classDAO.getByName(groupName);
            User mentor = userDAO.getByLogin(mentorLogin);
            boolean isAdded = userClassDAO.add(aClass.getId(), mentor.getId());
            if (isAdded) {
                classView.displayGroupConnectionAdded();
            } else {
                classView.displayErrorAddingGroupConnection();
            }
        } else {
            classView.displayThereIsNoGroupWithThisName();
        }
    }

    void revokeMentorFromGroup() {
        List<User> mentors = new ArrayList<>(userDAO.getAllByRole(2));
        classView.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            classView.pressAnyKeyToContinue();
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
        List<Class> classes = new ArrayList<>(classDAO.getAll());
        classView.displayEntriesNoInput(classes);
        if (classes.isEmpty()) {
            classView.pressAnyKeyToContinue();
            return;
        }
        String groupName = classView.getTeamNameInput();
        if (classDAO.getByName(groupName) != null) {
            Class aClass = classDAO.getByName(groupName);
            User mentor = userDAO.getByLogin(mentorLogin);
            boolean isRemoved = userClassDAO.delete(aClass.getId(), mentor.getId());
            if (isRemoved) {
                classView.displayGroupConnectionRemoved();
            } else {
                classView.displayErrorRemovingGroupConnection();
            }
        } else {
            classView.displayThereIsNoGroupWithThisName();
        }
    }

    void deleteGroup() {
        List<Class> groups = new ArrayList<>(classDAO.getAll());
        classView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            classView.pressAnyKeyToContinue();
            return;
        }
        String groupName = classView.getTeamNameInput();
        Class aClass = classDAO.getByName(groupName);
        if (aClass != null) {
            classDAO.delete(aClass);
            classView.displayGroupDeleted();
        } else {
            classView.displayThereIsNoGroupWithThisName();
        }
    }

    void showMentorGroups(int mentorID) {
        List<String> groupsNames = new ArrayList<>(classDAO.getGroupsNamesByMentorId(mentorID));
        if (!groupsNames.isEmpty()) {
            for (String groupName : groupsNames) {
                classView.displayGroupName(groupName);
                Class aClass = classDAO.getByName(groupName);
                if (!userDAO.getStudentsByGroupId(aClass.getId()).isEmpty()) {
                    List<User> students = new ArrayList<>(userDAO.getStudentsByGroupId(aClass.getId()));
                    classView.displayEntriesNoInput(students);
                } else {
                    classView.displayThisGroupHasNoStudentsAssigned();
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

        List<Class> groups = new ArrayList<>(classDAO.getAll());
        mentorView.displayEntriesNoInput(groups);
        if (groups.isEmpty()) {
            mentorView.pressAnyKeyToContinue();
            return;
        }
        String groupName = mentorView.getGroupNameInput();
        if (classDAO.getByName(groupName) != null) {
            Class aClass = classDAO.getByName(groupName);
            User student = userDAO.getByLogin(studentLogin);
            StudentDetails studentDetails = studentDetailsDAO.getStudentDataByStudentId(student.getId());
            studentDetails.setGroupId(aClass.getId());
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

