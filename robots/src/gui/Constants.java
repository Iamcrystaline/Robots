package gui;

public interface Constants {

    class MainApplicationFrameConstants {

        public static final int SCREEN_OFFSET = 50;
        public static final int INITIAL_GAME_WINDOW_HEIGHT = 400;
        public static final int INITIAL_GAME_WINDOW_WIDTH = 400;
        public static final int INTERNAL_GAME_WINDOW_WIDTH = 388;
        public static final int INTERNAL_GAME_WINDOW_HEIGHT= 369;
        public static final int LOG_WINDOW_INITIAL_LOCATION_X = 10;
        public static final int LOG_WINDOW_INITIAL_LOCATION_Y = 10;
        public static final int LOG_WINDOW_INITIAL_WIDTH = 300;
        public static final int LOG_WINDOW_INITIAL_HEIGHT = 800;
        public static final String LOGGER_INITIAL_MESSAGE = "Протокол работает";
        public static final String EXIT_BUTTON_TEXT = "Выход";
        public static final String YES_BUTTON_TEXT_CONSTANT = "OptionPane.yesButtonText";
        public static final String YES_BUTTON_TEXT = "Да";
        public static final String NO_BUTTON_TEXT_CONSTANT = "OptionPane.noButtonText";
        public static final String NO_BUTTON_TEXT = "Нет";
        public static final String EXIT_CONFIRM_DIALOG_TEXT = "Вы уверены?";
        public static final String EXIT_CONFIRM_DIALOG_TITLE = "ВЫХОД";
        public static final String TEST_MENU_TEXT = "Тесты";
        public static final String TEST_LOG_OPTION_TEXT = "Сообщение в лог";
        public static final String TEST_LOG_TEXT = "Новая строка";
        public static final String STYLE_MENU_TEXT = "Режим отображения";
        public static final String CROSS_PLATFORM_STYLE_TEXT = "Универсальная схема";
        public static final String SYSTEM_STYLE_TEXT = "Системная схема";
        public static final String ROBOT_START_MOVING = "Робот двигается";
        public static final String ROBOT_STOP_MOVING = "Робот остановился";
    }

    class RobotsProgramConstants {

        public static final String NIMBUS_MENU_STYLE = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
        public static final String METAL_MENU_STYLE = "javax.swing.plaf.metal.MetalLookAndFeel";
    }

    class LogWindowConstants {

        public static final String INITIAL_LOG_MESSAGE = "Протокол работы";
        public static final int LOG_TEXT_AREA_WIDTH = 200;
        public static final int LOG_TEXT_AREA_HEIGHT = 500;
    }

    class GameWindowConstants {

        public static final String GAME_WINDOW_TITLE = "Игровое поле";
    }

    class TimerConstants {
        public static final String FREEZE_TIME_TIMER_NAME = "Freeze time timer";
        public static final String TIMER_NAME = "events generator";
        public static final int TIMER_DELAY = 10;
        public static final int TIMER_REDRAW_PERIOD = 50;
        public static final int TIMER_UPDATE_PERIOD = 10;

    }

    class RobotConstants {
        public static final double ROBOT_VELOCITY_MULTIPLIER = 1.2;
        public static final double ROBOT_ANGULAR_VELOCITY_MULTIPLIER = 1.2;
        public static final long ROBOT_VELOCITY_INCREASE_PERIOD = 30000L;
        public static final double MIN_ROBOT_SPAWN_DISTANCE = 200;
        public static final long ROBOT_SPAWN_PERIOD = 30000L;
        public static final int ROBOT_INITIAL_X_COORDINATE = 100;
        public static final int ROBOT_INITIAL_Y_COORDINATE = 100;
        public static final int ROBOT_INITIAL_DIRECTION = 0;
        public static final int ROBOT_BODY_FIRST_DIAMETER = 30;
        public static final int ROBOT_BODY_SECOND_DIAMETER = 10;
        public static final int ROBOT_HEAD_X_OFFSET = 10;
        public static final int ROBOT_HEAD_DIAMETER = 5;
        public static final double ROBOT_DEFAULT_VELOCITY = 0.1;
        public static final double ROBOT_ANGULAR_VELOCITY = 0.005;
        public static final double ROBOT_STOP_DISTANCE = 0.5;
    }

    class LoggerConstants {
        public static final int LOGGER_IQUEUE_LENGTH = 100;
    }

    class TargetConstants {
        public static final double TARGET_DEFAULT_VELOCITY = 0.1;
        public static final int TARGET_INITIAL_Y_COORDINATE = 100;
        public static final int TARGET_INITIAL_X_COORDINATE = 150;
        public static final int TARGET_ANCHORY = 0;
        public static final int TARGET_ANCHORX = 0;
        public static final int TARGET_THETA = 0;
        public static final int TARGET_DIAMETER = 5;
    }

    class VelocityEffectConstants {
        public static final String EFFECT_TIMER_NAME = "Effect timer";
    }

    class HasteEffectConstants {
        public static final double HASTE_EFFECT_MULTIPLIER = 2;
        public static final int HASTE_EFFECT_ANCHORX = 0;
        public static final int HASTE_EFFECT_THETA = 0;
        public static final int HASTE_EFFECT_ANCHORY = 0;
        public static final int HASTE_EFFECT_DIAMETER = 10;
        public static final long HASTE_EFFECT_DURATION = 30000L;
    }

    class SlowEffectConstants {
        public static final double SLOW_EFFECT_MULTIPLIER = 1.0 / 2;
        public static final int SLOW_EFFECT_ANCHORX = 0;
        public static final int SLOW_EFFECT_THETA = 0;
        public static final int SLOW_EFFECT_ANCHORY = 0;
        public static final int SLOW_EFFECT_DIAMETER = 10;
        public static final long SLOW_EFFECT_DURATION = 30000L;
    }

    class FreezeTimeAbilityConstants {
        public static final long FREEZE_TIME_ABILITY_COOLDOWN = 30000L;
        public static final long FREEZE_TIME_ABILITY_DURATION = 5000L;
    }

    class ShieldAbilityConstants {
        public static final long SHIELD_ABILITY_COOLDOWN = 30000L;
        public static final long SHIELD_ABILITY_DURATION = 5000L;
        public static final long SHIELD_ABILITY_STUN_DURATION = 3000L;
    }
}
