/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

/**
 *
 * @author arielmota
 */
public class Autenticacao {

    public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

}

