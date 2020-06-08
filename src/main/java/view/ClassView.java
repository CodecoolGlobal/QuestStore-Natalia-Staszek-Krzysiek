package view;

public class ClassView extends View {

    public String getTeamNameInput() {
        System.out.println("Enter group name: ");
        return getStringInput();
    }

    public void displayGroupAdded() {
        System.out.println("Group has been added!");
        pressAnyKeyToContinue();
    }

    public void displayGroupWithThisNameAlreadyExists() {
        System.out.println("There is no group with this name!");
        pressAnyKeyToContinue();
    }

    public void displayThereIsNoGroupWithThisName() {
        System.out.println("There is no group with this name!");
        pressAnyKeyToContinue();
    }

    public void displayGroupDeleted() {
        System.out.println("Group has been deketed!");
        pressAnyKeyToContinue();
    }

    public void displayGroupConnectionAdded() {
        System.out.println("Group connection has been added!");
        pressAnyKeyToContinue();
    }

    public void displayErrorAddingGroupConnection() {
        System.out.println("Error adding a connection!");
        pressAnyKeyToContinue();
    }

    public void displayGroupConnectionRemoved() {
        System.out.println("Group connection has been removed!");
        pressAnyKeyToContinue();
    }

    public void displayErrorRemovingGroupConnection() {
        System.out.println("Error removing a group connection!");
        pressAnyKeyToContinue();
    }

    public void displayGroupName(String groupName) {
        System.out.println("");
        System.out.println("Group name: " + groupName + " | Students in the group: ");
    }

    public void displayThisGroupHasNoStudentsAssigned() {
        System.out.println("This group has no students assigned!");
        pressAnyKeyToContinue();
    }
}
