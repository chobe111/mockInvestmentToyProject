package com.parker.myapplication.page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentLoginBinding
import com.parker.myapplication.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    /*private: 자신의 클래스에서만 접근이 가능. 정보은닉. 객체 변수를 숨기는 기능
    lateinit: null을 허용으로 선언하지 않지만 프로퍼티 초기화를 미룸. '늦은 초기화'
    view binding. lateinit를 이용하여 onCreate()콜백 메소드가 호출된 후에 생성*/

    private lateinit var binding: FragmentMainBinding
    private lateinit var listener: OnButtonClickEvent

    interface OnButtonClickEvent {
        fun onLoginButtonClick()
        fun onRegisterButtonClick()
    }

    private fun setClickEvent() {
        val loginButton = binding.loginButton
        val registerButton = binding.registerButton

        loginButton.setOnClickListener {
            listener.onLoginButtonClick()
        }

       registerButton.setOnClickListener {
            listener.onRegisterButtonClick()
        }
    }
/*override:부모 Class에서 정의한 메서드를 자식 Class에서 변경하는 것. 자식 class에서 재정의한
* 내용을 최우선으로 함. 메서드이름이 같아야하며 매개변수, 리턴 타입이 같아야 함.*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    //각 binding class에는 inflate() 메소드가 포함되어 있다. 이 메소드를 호출시킴으로써 binding class의 인스턴스가 생성되게 된다.
    binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        setClickEvent()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnButtonClickEvent
    }

}