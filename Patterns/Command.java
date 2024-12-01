package Patterns;

interface ICommand {
    void execute();
}

class Light {
    void on() { System.out.println("Light is on"); }
    void off() { System.out.println("Light is off"); }
}

class RemoteControl {
    private ICommand command;
    void setCommand(ICommand command) { this.command = command; }
    void pressButton() { command.execute(); }
}

/**
 * Command - behavioral pattern, turns the queries into objects, allows parametrising
 *              methods with other queries, put queries into queue, etc.
 *
 * - to separate dispatcher of the request from the object, which executes the action
 * - to implement the queues of the commands
 * - to log the commands with replay capabilities
 *
 * Benefits:
 * - Incapsulate operations into objects, easy to add new commands without modifying the executor
 * - Flexible in how the control is handled, the execution can be delayed, declined, keep for next execution in queues or transactions
 * - Allows combining the commands, create microcommands for sequences of execution
 * - Maintains open-close principle, can add new commands without modifying existing code
 *
 * Drawbacks:
 * - We need to keep the commands history to be able to cancel some commands
 * - Adding new command requires new classes, this increases the amount of work and increases the complexity of the system
 */

public class Command {

    public static void main(String[] args) {
        Light light = new Light();
        RemoteControl rc = new RemoteControl();

        rc.setCommand(light::on);
        rc.pressButton();

        rc.setCommand(light::off);
        rc.pressButton();
    }
}
