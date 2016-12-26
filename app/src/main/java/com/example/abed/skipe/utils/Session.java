package com.example.abed.skipe.utils;


import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Intent;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;


import android.util.Log;

import com.example.abed.skipe.activites.LoginActivity;
import com.example.abed.skipe.model.Student;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Session {
        // define single instance
        private static Session instance;
        // define realm
        private Realm realm;

        // Session constructor
        private Session() {
            RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            realm = Realm.getInstance(realmConfig);
        }

        // get singletone from session
        public static Session getInstance() {
            if (instance == null) {
                instance = new Session();
            }
            return instance;
        }

        // get new Instance (new Object) from this class
        public static Session newInstance() {
            return new Session();
        }

        // login user take user and add it to realm
        public void loginUser(final Student user) {

            if (realm.where(Student.class).findFirst() == null) {

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealm(user);
                    }
                });

            } else {
                logout();
                loginUser(user);
            }


        }

        // logout
        public void logout() {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.delete(Student.class);
                    realm.deleteAll();
                }
            });
        }

        public Boolean isUserLoggedIn() {
            return realm.where(Student.class).findFirst() != null;
        }

        public Student getUser() {
            return realm.where(Student.class).findFirst();
        }

        public void logoutAndGoToLogin(Activity activity) {
            logout();
            activity.startActivity(new Intent(activity, LoginActivity.class));
            activity.finish();
        }


    }