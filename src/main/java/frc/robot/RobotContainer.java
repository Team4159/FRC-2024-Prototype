package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.FeederConstants.FeederState;
import frc.robot.Constants.IntakeConstants.IntakeState;
import frc.robot.Constants.JoystickConstants.Primary;
import frc.robot.Constants.JoystickConstants.Secondary;
import frc.robot.Constants.ShooterConstants.ShooterState;
import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick primary = new Joystick(Primary.port);
    private final Joystick secondary = new Joystick(Secondary.port);

    /* Drive Controls */
    private final int translationAxis = Joystick.AxisType.kY.value;
    private final int strafeAxis = Joystick.AxisType.kX.value;
    private final int rotationAxis = Joystick.AxisType.kZ.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(primary, Primary.zeroGyroButton);
    private final JoystickButton robotCentric = new JoystickButton(primary, Primary.robotCentricButton);

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final Shooter shooter = new Shooter();
    private final Intake intake = new Intake();
    private final Feeder feeder = new Feeder();

    /* Subsystem Buttons */
    /* Intake / Feeeder */
    private final JoystickButton intakeButton = new JoystickButton(secondary, Constants.JoystickConstants.Secondary.intakeButton);
    private final JoystickButton feederButton = new JoystickButton(secondary, Constants.JoystickConstants.Secondary.feederButton);
    private final JoystickButton intakeFeedButton = new JoystickButton(secondary, Constants.JoystickConstants.Secondary.intakeFeedButton);
    private final JoystickButton outtakeFeedButton = new JoystickButton(secondary, Constants.JoystickConstants.Secondary.outtakeFeedButton);
    
    /* Shooter */
    private final JoystickButton shooterButton = new JoystickButton(secondary, Secondary.shooterButton);
    private final JoystickButton shooterReverseButton = new JoystickButton(secondary, Secondary.shooterReverseButton);
    //private final JoystickButton intakeFeedShootButton = new JoystickButton(secondary, Secondary.intakeFeedShootButton);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -primary.getRawAxis(translationAxis), 
                () -> -primary.getRawAxis(strafeAxis), 
                () -> -primary.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
            )
        );

        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));

        /* Subsystem Buttons */
        shooterButton.onTrue(new InstantCommand(() -> shooter.setState(ShooterState.SHOOT)))
                    .onFalse(new InstantCommand(() -> shooter.setState(ShooterState.OFF)));
        shooterReverseButton.onTrue(new InstantCommand(() -> shooter.setState(ShooterState.REVERSE)))
                            .onFalse(new InstantCommand(() -> shooter.setState(ShooterState.OFF)));
        intakeButton.onTrue(new InstantCommand(() -> intake.setState(IntakeState.INTAKE)))
                            .onFalse(new InstantCommand(() -> intake.setState(IntakeState.OFF)));
        feederButton.onTrue(new InstantCommand(() -> feeder.setState(FeederState.FEED)))
                            .onFalse(new InstantCommand(() -> feeder.setState(FeederState.OFF)));
        intakeFeedButton.whileTrue(new IntakeFeed(intake, feeder, IntakeState.INTAKE, FeederState.FEED));
        outtakeFeedButton.whileTrue(new IntakeFeed(intake, feeder, IntakeState.OUTTAKE, FeederState.OUTTAKE));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new exampleAuto(s_Swerve);
    }
}
