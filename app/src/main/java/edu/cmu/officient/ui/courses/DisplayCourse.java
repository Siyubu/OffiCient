/*
 *
 *  * @author Solange Iyubu
 *  * AndrewID : siyubu
 *  * Program : MSECE
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.courses;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cmu.officient.R;
public class DisplayCourse extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) 
    {

        return inflater.inflate(R.layout.fragment_display_course, container, false);
    }

}
