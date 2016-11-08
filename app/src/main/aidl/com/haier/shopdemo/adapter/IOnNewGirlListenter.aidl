// IOnNewGirlListenter.aidl
package com.haier.shopdemo.adapter;
import  com.haier.shopdemo.adapter.Girl;
// Declare any non-default types here with import statements

interface IOnNewGirlListenter {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
  void onNewGirl(in Girl newGirl);
}
