package controller;

import dao.StudentDetailsDAO;
import dao.StudentItemDAO;
import model.StudentDetails;
import view.UserView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TeamController {
    private StudentDetailsDAO studentDetailsDAO;
    private StudentItemDAO studentItemDAO;
    private Map<String, Integer> teamMembersCount;
    private UserView userView;

    public TeamController(StudentDetailsDAO studentDetailsDAO, StudentItemDAO studentItemDAO, UserView userView) {
        this.studentDetailsDAO = studentDetailsDAO;
        this.studentItemDAO = studentItemDAO;
        this.userView = userView;
    }

    public void handleReshuffleStudentsTeams() {
        List<StudentDetails> students = studentDetailsDAO.getAllStudentsData();
        List<StudentDetails> teams = reshuffleStudentsTeam(students);

        updateDbStudentsTeam(teams);
        studentItemDAO.removeTeamItems();
    }

    private List<StudentDetails> reshuffleStudentsTeam(List<StudentDetails> students) {
        int numberOfTeams = countNumbersOfTeams(students);
        return assignStudentsToTeams(students, numberOfTeams);
    }

    private int countNumbersOfTeams(List<StudentDetails> students) {
        final int NUMBER_OF_TEAM_MEMBERS = 3;
        final int createTeam = 1;
        int numberOfStudents = students.size();
        int numberOfTeams;

        if (sizeIsEven(numberOfStudents, NUMBER_OF_TEAM_MEMBERS)) {
            numberOfTeams = numberOfStudents / NUMBER_OF_TEAM_MEMBERS;
        }
        else {
            numberOfTeams = numberOfStudents / NUMBER_OF_TEAM_MEMBERS + 1;
        }

        if (numberOfTeams < 1) numberOfTeams = createTeam;

        return numberOfTeams;
    }

    private boolean sizeIsEven(int numberOfStudents, int NUMBER_OF_TEAM_MEMBERS) {
        return numberOfStudents % NUMBER_OF_TEAM_MEMBERS == 0;
    }

    private char convertNumberToChar(int number) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return alphabet.charAt(number);
    }

    private List<StudentDetails> assignStudentsToTeams(List<StudentDetails> students, int numberOfTeams) {
        List<StudentDetails> studentsWithoutTeams = setAllStudentsNotAssignToTeam(students);

        if (numberOfTeams > 1) {
            return assignToRandomTeams(studentsWithoutTeams, numberOfTeams);
        }
        else {
            return assignAllToOneTeam(studentsWithoutTeams);
        }
    }

    private List<StudentDetails> setAllStudentsNotAssignToTeam(List<StudentDetails> students) {
        for (StudentDetails student : students) {
            student.setTeamName(null);
        }
        return students;
    }

    private List<StudentDetails> assignToRandomTeams(List<StudentDetails> students, int numberOfTeams) {
        teamMembersCount = new HashMap<>();
        Random randomNumber = new Random();
        boolean isAllAssigned = false;
        int index = 0;

        while (!isAllAssigned) {
            int randomIndex = randomNumber.nextInt(numberOfTeams);
            String randomTeam = String.valueOf(convertNumberToChar(randomIndex));

            if (isPossibilityToAssign(randomTeam)) {
                students.get(index).setTeamName(randomTeam);

                isAllAssigned = checkIfAllStudentsHaveTeam(students);
                ++index;
            }
        }
        return students;
    }

    private boolean isPossibilityToAssign(String randomTeam) {
        final int MAX = 3;

        if (!teamMembersCount.containsKey(randomTeam)) {
            teamMembersCount.put(randomTeam, 1);
        }
        else if (teamMembersCount.get(randomTeam) < MAX) {
            teamMembersCount.put(randomTeam, teamMembersCount.get(randomTeam) + 1);
        }
        else {
            return false;
        }
        return true;
    }

    private boolean checkIfAllStudentsHaveTeam(List<StudentDetails> students) {
        for (StudentDetails student : students) {
            if (student.getTeamName() == null) return false;
        }
        return true;
    }

    private List<StudentDetails> assignAllToOneTeam(List<StudentDetails> students) {
        int INDEX_OF_TEAM = 0;

        for (StudentDetails student : students) {
            String team = String.valueOf(convertNumberToChar(INDEX_OF_TEAM));
            student.setTeamName(team);
        }
        return students;
    }

    private void updateDbStudentsTeam(List<StudentDetails> students) {
        boolean isUpdated = false;

        for (StudentDetails student : students) {
            isUpdated = studentDetailsDAO.updateStudentData(student);
        }

        if (isUpdated) {
            userView.clearConsole();
            userView.operationSuccessMessage();
        }
    }
}