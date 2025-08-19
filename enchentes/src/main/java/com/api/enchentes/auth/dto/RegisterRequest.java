package com.api.enchentes.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@NotBlank(message = "E-mail é obrigatório")
                              @Email(message = "Formato de e-mail inválido")
                              String username,

                              @NotBlank(message = "Senha é obrigatória")
                              @Size(min = 11, message = "Senha deve ter mais de 10 caracteres")
                              @Pattern(
                                      regexp = "^(?=.*[!@#$%^&*()_+\\-\\[\\]{};':\\\"\\\\|,.<>/?]).+$",
                                      message = "Senha deve conter ao menos 1 caractere especial"
                              )
                              String password) {}
