package controller;

import dao.QuestDAO;
import dao.StudentQuestDAO;
import dao.UserDAO;
import model.Quest;
import model.User;
import view.QuestView;

import java.util.ArrayList;
import java.util.List;

public class QuestController {

    private final String BASIC_TASK = "b";
    private QuestDAO questDAO;
    private UserDAO userDAO;
    private StudentQuestDAO studentQuestDAO;
    private StudentController studentController;
    private QuestView questView;

    public QuestController(QuestDAO questDAO, UserDAO userDAO, StudentQuestDAO studentQuestDAO, StudentController studentController,
                            QuestView questView) {
        this.questDAO = questDAO;
        this.userDAO = userDAO;
        this.studentQuestDAO = studentQuestDAO;
        this.studentController = studentController;
        this.questView = questView;
    }

    void addNewQuest(int idMentor) {
        String questName = questView.getQuestNameInput();
        if (questDAO.getByName(questName) != null) {
            questView.displayQuestAlreadyExists();
        } else {
            int points = questView.getQuestPointsInput();
            String description = questView.getQuestDescriptionInput();
            String categoryInput = questView.getQuestCategory();
            String category = categoryInput.equals(BASIC_TASK) ? "BASIC" : "EXTRA";
            if (questDAO.add(new Quest(idMentor, questName, points, description, category))) {
                questView.displayQuestSuccessfullyAdded();
            } else {
                questView.displayErrorAddingQuest();
            }
        }
    }

    void editQuest() {

        List<Quest> quests = new ArrayList<>(questDAO.getAll());
        questView.displayEntriesNoInput(quests);
        if (quests.isEmpty()) {
            questView.pressAnyKeyToContinue();
            return;
        }
        String taskName = questView.getQuestNameInput();
        if (questDAO.getByName(taskName) != null) {
            updateQuest(questDAO.getByName(taskName));
        } else {
            questView.displayThereIsNoTaskWithThisName();
        }
    }

    private void updateQuest(Quest quest) {
        final String UPDATE_POINTS = "1";
        final String UPDATE_DESCRIPTION = "2";
        final String UPDATE_CATEGORY = "3";

        switch(questView.getValueToUpdate(quest)) {
            case UPDATE_POINTS:
                int points = questView.askForPointsInput();
                quest.setPoints(points);
                showEditResultMessage(questDAO.update(quest));
                break;
            case UPDATE_DESCRIPTION:
                String description = questView.askForDescriptionInput();
                quest.setDescription(description);
                showEditResultMessage(questDAO.update(quest));
                break;
            case UPDATE_CATEGORY:
                String categoryInput = questView.getQuestCategory();
                String category = categoryInput.equals(BASIC_TASK) ? "basic" : "extra";
                quest.setCategory(category);
                showEditResultMessage(questDAO.update(quest));
                break;
            default:
                questView.displayWrongOptionMessage();
        }
    }

    private void showEditResultMessage(boolean isEdit) {
        if (isEdit) {
            questView.valueChanged();
        } else {
            questView.errorChangingValue();
        }
    }

    void markStudentAchievedQuest() {

        List<User> students = new ArrayList<>(userDAO.getAllByRole(3));
        questView.displayEntriesNoInput(students);
        if (students.isEmpty()) {
            questView.pressAnyKeyToContinue();
            return;
        }
        String studentLogin = questView.getCodecoolerLoginToMarkQuest();
        if (userDAO.getByLoginAndRole(studentLogin, 3) != null) {
            choseQuestToMark(studentLogin);
        } else {
            questView.displayThereIsNoStudentWithThisLogin();
        }
    }

    private void choseQuestToMark(String studentLogin) {

        List<Quest> quests = new ArrayList<>(questDAO.getAll());
        questView.displayEntriesNoInput(quests);
        if (quests.isEmpty()) {
            questView.pressAnyKeyToContinue();
            return;
        }
        String taskName = questView.getQuestNameInput();
        if (questDAO.getByName(taskName) != null) {
            Quest quest = questDAO.getByName(taskName);
            User student = userDAO.getByLogin(studentLogin);
            boolean isAdded = studentQuestDAO.add(student.getId(), quest.getId());
            if (isAdded) {
                questView.displayQuestConnectionAdded();
                studentController.updateStudentBalance(student.getId(), quest.getPoints());
                studentController.updateStudentExperienceAndLevel(student.getId(), quest.getPoints());
            } else {
                questView.displayErrorAddingQuestConnection();
            }
        } else {
            questView.displayThereIsNoTaskWithThisName();
        }
    }
}