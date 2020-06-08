package controller;

import dao.UserDAO;
import model.User;
import view.MentorView;

import java.util.ArrayList;
import java.util.List;

public class MentorController {

    private MentorView mentorView;
    private UserDAO userDAO;
    private ClassController classController;

    public MentorController(UserDAO userDAO, ClassController classController, MentorView mentorView) {
        this.userDAO = userDAO;
        this.classController = classController;
        this.mentorView = mentorView;
    }

    void deleteMentor() {
        List<User> mentors = new ArrayList<>(userDAO.getAllByRole(2));
        mentorView.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            mentorView.pressAnyKeyToContinue();
            return;
        }
        String login = mentorView.getMentorLoginToDelete();
        User mentor = userDAO.getByLoginAndRole(login, 2);
        if (mentor != null) {
            userDAO.delete(mentor);
            mentorView.displayMentorDeletedMessage();
        } else {
            mentorView.displayNoMentorMessage();
        }
    }

    void showMentorProfileAndHisGroups() {
        List<User> mentors = new ArrayList<>(userDAO.getAllByRole(2));
        mentorView.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            mentorView.pressAnyKeyToContinue();
            return;
        }
        String login = mentorView.getMentorLoginToShowProfile();
        User mentor = userDAO.getByLoginAndRole(login, 2);

        if (mentor != null) {
            mentorView.displayMentorProfile(mentor);
            classController.showMentorGroups(mentor.getId());
        } else {
            mentorView.displayNoMentorMessage();
        }
    }

    void editMentorData() {

        final String QUIT_OPTION = "q";

        List<User> mentors = new ArrayList<>(userDAO.getAllByRole(2));
        mentorView.displayEntriesNoInput(mentors);
        if (userDAO.getAllByRole(2).isEmpty()) {
            mentorView.pressAnyKeyToContinue();
            return;
        }
        String login = mentorView.getMentorLoginToEdit();
        if (login.equals(QUIT_OPTION)) return;
        User mentorToEdit = userDAO.getByLoginAndRole(login, 2);
        if (mentorToEdit != null) {
            updateProfileAttribute(mentorToEdit);
        } else {
            mentorView.displayThereIsNoMentorWithThisLogin();
        }
    }

    private void updateProfileAttribute(User user) {
        final int UPDATE_NAME = 1;
        final int UPDATE_LOGIN = 2;
        final int UPDATE_EMAIL = 3;
        final int UPDATE_PHONE = 4;

        int valueToChange = mentorView.askForChangeInProfile(user);
        switch (valueToChange) {
            case UPDATE_NAME:
                String name = mentorView.askForNewValue();
                user.setName(name);
                showEditResultMessage(userDAO.update(user));
                break;
            case UPDATE_LOGIN:
                String login = mentorView.askForNewValue();
                user.setLogin(login);
                showEditResultMessage(userDAO.update(user));
                break;
            case UPDATE_EMAIL:
                String email = mentorView.askForNewValue();
                user.setEmail(email);
                showEditResultMessage(userDAO.update(user));
                break;
            case UPDATE_PHONE:
                String phoneNumber = mentorView.askForNewValue();
                user.setPhoneNumber(phoneNumber);
                showEditResultMessage(userDAO.update(user));
                break;
            default:
                mentorView.errorWrongSign();
                break;
        }
    }

    private void showEditResultMessage(boolean isEdit) {
        if (isEdit) {
            mentorView.valueChanged();
        } else {
            mentorView.errorChangingValueMessage();
        }
    }
}