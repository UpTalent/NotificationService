package io.github.uptalent.notification.model.constant;

public class EmailMessageConstant {
    public static final String MESSAGE_HEADER = """
                    <div style="display: grid;\040
                        flex-direction: column;\040
                        border-radius: 10px;\040
                        padding: 20px;\040
                        font-size: larger;\040
                        border: 3px solid #48bde2;\040
                        margin: 3px;">
                        <h1 style="color: #48bde2;\040
                        margin: 0;">""";
    public static final String MESSAGE_FOOTER = """
                <h4 style="margin-bottom: 0;">
                            Best regards,
                        </h4>
                        <div style="display: flex; align-items: center; gap: 5px;">
                        <img src="https://drive.google.com/uc?export=view&id=1Fx1E7h2r8ly23p5LCD5Jpc_slgUPSu2I" alt="logo" style="width:30px; object-fit: contain;"/>
                        <h4 style="margin-left: 5px;">
                            UPTALENT
                        </h4>
                        </div>
                        </div>
                """;
}
