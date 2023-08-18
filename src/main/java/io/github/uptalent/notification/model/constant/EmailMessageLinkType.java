package io.github.uptalent.notification.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailMessageLinkType {
    VERIFY("/verify",
            "Your UPTalent account not activated",
            """
                        Verify your account
                        </h1>
                        <h3 style="margin: 10px 0;">
                            Dear %s,
                        </h3>
                        <p>
                            Someone created an account with your email
                        </p>
                        <p>
                            If it wasn't you, then you don't need to do anything.
                        </p>
                        <p>
                            If it you, please activate it until <b>%s</b>
                        </p>
                        <p>\040
                            Please click the button down bellow and follow the instructions.
                        </p>
                        <a href="%s" style="width: 200px; height: 50px; align-self: center;">
                        <button style="background: linear-gradient(87.27deg, rgb(156, 218, 237) 3.18%%, rgb(0, 147, 193) 63.05%%);
                        cursor: pointer;\040
                        color:white;\040
                        font-weight: bold;\040
                        width: 200px;\040
                        height: 50px;\040
                        border: none;\040
                        border-radius: 10px;">
                            Verify your account
                        </button>
                        </a>
                    """),
    RESTORE("/restore",
            "Your UpTalent account was temporary deleted",
            """
                    Restore your account
                        </h1>
                        <h3 style="margin: 10px 0;">
                            Dear %s,
                        </h3>
                        <p>
                            We are sorry to hear that you have deleted your account, so to restore it no later than <b>%s</b>
                        </p>
                        <p>\040
                            Please click the button down bellow and follow the instructions.
                        </p>
                        <a href="%s" style="width: 200px; height: 50px; align-self: center;">
                        <button style="background: linear-gradient(87.27deg, rgb(156, 218, 237) 3.18%%, rgb(0, 147, 193) 63.05%%);
                        cursor: pointer;\040
                        color:white;\040
                        font-weight: bold;\040
                        width: 200px;\040
                        height: 50px;\040
                        border: none;\040
                        border-radius: 10px;">
                            Restore your account
                        </button>
                        </a>
                """),
    CHANGE_PASSWORD("/password/change",
            "Your UPTalent account has request to change password",
            """
                    Change password of your account
                        </h1>
                        <h3 style="margin: 10px 0;">
                            Dear %s,
                        </h3>
                        <p>
                            We received your request for changing password, so do it no later than <b>%s</b>
                        </p>
                        <p>\040
                            Please click the button down bellow and follow the instructions.
                        </p>
                        <a href="%s" style="width: 200px; height: 50px; align-self: center;">
                        <button style="background: linear-gradient(87.27deg, rgb(156, 218, 237) 3.18%%, rgb(0, 147, 193) 63.05%%);
                        cursor: pointer;\040
                        color:white;\040
                        font-weight: bold;\040
                        width: 200px;\040
                        height: 50px;\040
                        border: none;\040
                        border-radius: 10px;">
                            Change password of your account
                        </button>
                        </a>
                """);

    private final String url;
    private final String subject;
    private final String messageBody;
}
