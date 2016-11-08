// IMyAidlInterface.aidl
package com.haier.shopdemo.adapter;
import  com.haier.shopdemo.adapter.Girl;
import  com.haier.shopdemo.adapter.IOnNewGirlListenter;
// Declare any non-default types here with import statements


interface IMyAidlInterface {

//            String sayHello();
//            Girl getGirl();
              List<Girl> getGirleList();
              void addGirle(in Girl girle);
              void registerListenter(IOnNewGirlListenter listneter);
              void unregisterListenter(IOnNewGirlListenter listneter);
}