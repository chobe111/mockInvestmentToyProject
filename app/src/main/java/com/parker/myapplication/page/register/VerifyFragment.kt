package com.parker.myapplication.page.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentVerifyBinding

class VerifyFragment : Fragment() { // 2/25 이거 보면서 뷰 바인딩 익히기
    private lateinit var binding: FragmentVerifyBinding // fragment_verify.xml이기 때문에 FragmentVerifyBinding라는 클래스가 만들어짐.
    // 클래스의 변수를 참조하고 싶으면 아래처럼 binding.뷰 ID로 불러오면 됨.

    private lateinit var authenticateNumber: EditText // 여기서 바인딩 등록(?)해줌. private lateinit var 아이디: 항목 형식. 여기서 등록을 해야 아래에서 binding 가능.
    private lateinit var phoneNumber: EditText // 여기서 lateinit의 역할은 ?와 같다. 빈칸으로 남겨둘건데 언젠가 채울거니까(init) 놔둬라 라는 뜻.
    private lateinit var verifyRequestButton: Button // private은 이 클래스 안에서만 쓰겠다는 뜻.
    private lateinit var registerSignUpButton: Button // 변수의 이름은 편의상 불러오고 싶은 xml 파일 속 요소의 아이디를 그대로 가져오기.



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVerifyBinding.inflate(inflater, container, false) // attachToParent는 argument. 적어주지 않아도 false만 쓰면 알아서 써짐
        // inflater 인자를 가져와 뷰와 연결.
        val view = binding.root
            authenticateNumber = binding.authenticateNumber // 편의상 xml 파일의 아이디와 같은 이름으로 쓴 변수가 위에서 만들어지고 나서
            phoneNumber = binding.phoneNumber // onCreateView 함수 안에서 변수 이름 = binding.ID의 형식으로 바인딩됨.
            verifyRequestButton = binding.verifyRequestButton
            registerSignUpButton = binding.registerSignUpButton // 여기서는 변수니까 registerSignUpButton() 이런 식으로 소괄호가 붙어선 안됨.

        verifyRequestButton.setOnClickListener{authenticateNumber.setBackgroundResource(R.drawable.box_shape_fill)
        authenticateNumber.isEnabled = true} // 이걸로 verifyRequestButton 즉, 인증요청 버튼이 눌려서 박스 색깔이 box_shape에서 box_shape_fill이 되도록 만들기.
        // 궁금한 점 그럼 이 코드들은 무엇인가. 무엇으로 이뤄져 있길래 어떤 역할을 하는 것인가.
        return view // 그리고나서 위에 쓰여진 view를 리턴. 참고로 view란 레이아웃 속 각 요소들을 일컬음.(ex) id가 phoneNumber인 EditText가 하나의 뷰)
    }
}