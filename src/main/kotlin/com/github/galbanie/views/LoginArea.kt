package com.github.galbanie.views

import com.github.galbanie.Styles
import com.github.galbanie.controllers.UserController
import com.github.galbanie.models.UserModel
import javafx.beans.binding.BooleanExpression
import javafx.geometry.Orientation
import javafx.scene.control.Alert.AlertType.INFORMATION
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.layout.StackPane
import tornadofx.*

class LoginArea : View("Login") {
    val userController : UserController by inject()
    val model : UserModel by inject()

    val messageWrapper by cssid()
    val passwordField by cssid()

    init {
        disableSave()
        disableDelete()
        disableRefresh()
    }

    override val root = vbox {
        addClass(Styles.loginScreen)

        label().addClass(Styles.logoIcon, Styles.icon, Styles.large)
        label(title).addClass(Styles.h1)
        stackpane().setId(messageWrapper)
        form {
            fieldset(labelPosition = Orientation.VERTICAL) {
                field("Username or email address") {
                    textfield(model.username) {
                        required(message = "Enter your login name")
                    }
                }
                field("Password") {
                    passwordfield(model.password) {
                        setId(passwordField)
                        required(message = "Enter your password")
                    }
                }.forgotPasswordLink()
            }

            button("Sign in") {
                isDefaultButton = true
                addClass(Styles.successButton)
                action {
                    login()
                }
            }
        }
        hbox {
            addClass(Styles.newToGitHub)
            text("New to GitHub? ")
            hyperlink("Create an account.") {
                setOnAction {
                    hostServices.showDocument("https://github.com/join?source=login")
                }
            }
        }
        hbox {
            addClass(Styles.footer)
            label("Dictionary PartSoft Module")
        }
    }

    private fun Button.login() {
        // Temporarily change the text and opacity of the login button
        fun signalSigningIn() {
            properties["originalText"] = text
            text = "Signing in..."
            opacity = 0.5
        }

        // Reset the text and opacity
        fun signalSigningComplete() {
            text = properties["originalText"] as String
            opacity = 1.0
        }

        if (model.commit()) {
            signalSigningIn()

            runAsync {
                userController.login(model.username.value, model.password.value)
            } ui { success ->
                signalSigningComplete()

                if (success) {
                    workspace.dock<DictionaryArea>()
                } else {
                    loginFailed()
                }
            }
        }

    }


    /**
     * Locate the messageWrapper by it's CSS id and replace it's content
     * with a "login failed" error message. Then focus the password field.
     */
    private fun loginFailed() {
        root.select<StackPane>(messageWrapper).replaceChildren {
            hbox {
                addClass(Styles.errorMessage)
                label("Incorrect username or password.")
                spacer()
                button {
                    addClass(Styles.crossIcon, Styles.icon, Styles.small)
                    action {
                        this@hbox.removeFromParent()
                    }
                }
            }
        }

        root.select<PasswordField>(passwordField).requestFocus()
    }

    fun Field.forgotPasswordLink() {
        label.style { minWidth = 170.px }
        labelContainer.hyperlink("Forgot password?") {
            isFocusTraversable = false
            style { fontSize = 12.px }
        }
    }
}