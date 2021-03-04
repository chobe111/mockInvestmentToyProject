package com.parker.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.parker.myapplication.databinding.ActivityMainBinding //
import com.parker.myapplication.page.main.LoginFragment
import com.parker.myapplication.page.main.MainFragment
import com.parker.myapplication.page.register.ExampleFragment
import com.parker.myapplication.page.register.RegisterFragment
import com.parker.myapplication.page.register.VerifyFragment

class MainActivity : AppCompatActivity(), MainFragment.OnButtonClickEvent, RegisterFragment.OnRegisterDoneListener {

    // 2/9 RegisterFragment.OnRegisterDoneListener 이걸 추가 안해서 그동안 회원가입 버튼을 눌렀을 때 튕긴 것.
    // private lateinit var binding: ActivityMainBinding

    private var _binding: ActivityMainBinding? = null
    // activity_main.xml이 있기 때문에 바인딩 클래스는 ActivityMainBinding이 됨.
    // 뷰바인딩 가이드 1. 전역 변수로 바인딩 객체 선언
    // private: 해당 클래스 or 인터페이스 내부에서만 사용 가능.
    // 코틀린에서 외부 클래스는 내부 클래스의 private 멤버를 사용할 수 없음.
    private val binding get() = _binding!! // !!는 null이 아니다. 무조건 값이 있음을 보장.
    // 2. 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언

    private val mainFragment : MainFragment by lazy { MainFragment() } // lazy 속성은 lateinit과 함께 늦은 초기화를 해주는 요소이다.
    // 코틀린에서의 전역변수는 초기화가 필요하기 때문에 무언가 입력되기 전 null로 잠시 남겨놔야 하는 경우는 늦은 초기화 필요.
    private val loginFragment : LoginFragment by lazy { LoginFragment() }
    private val registerFragment : RegisterFragment by lazy { RegisterFragment() }
    private val verifyFragment : VerifyFragment by lazy { VerifyFragment() } // 2/25 여기서 verifyFragment 연결

    private val exampleFragment by lazy { ExampleFragment() } // 2/23 ExampleFragment 작업하면서 새로 쓴 코드

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // 여기서 시작임. savedInstanceState는 Activity의 이전 저장 상태가
        // 포함된 Bundle 객체임. 처음 생성된 활동인 경우 Bundle 객체의 값은 null임.
        // 그래서 Bundle?에서 ?이 사용됨. 매개변수에 전달된 arguments가 null일 수 있다는 의미.
        super.onCreate(savedInstanceState)
        // 3. 기존 setContentView를 제거하고
        // setContentView(R.layout.activity_main)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        // 4. 자동 생성된 뷰 바인딩 클래스에서 inflate라는 메서드를 활용하여
        // 액티비티에 사용할 바인딩 클래스의 인스턴스 생성하여 뷰와 연결.
        val view = binding.root
        // 5. view 변수 정의.
        setContentView(view) //UI 넣어 주는 곳. setContnetView에 view가 파라미터로 들어가 있음.
        // 즉, xml과 kt 소스코드를 한 쌍으로 맺어주는 것이 setContentView임.
        // 자세히 설명하자면, xml 소스코드에 버튼을 배치했을 때, 버튼을 클릭하여 이벤트를 발생시키고 싶다면
        // 버튼(view)의 아이디를 '참조'하여 kt 소스코드에서 '참조'하여 사용할 것이다.
        // 즉, xml에 배치된 view(버튼이나 텍스트뷰)는 kt소스코드에 쓰이기 때문에 객체화가 되어야 함.
        // 역으로 객체화가 되어 있어야 코틀린 파일에서 객체들을 참조해 기능을 적용 가능. 이때 객체화 시키는 기능을 제공하는 메소드가
        // setContentView()다. xml 레이아웃들이 객체화되는 과정은 메모리에 로딩되어 화면(뷰 그룹)으로 드러나는데, 이 과정을 inflation이라고 함.
        changeFragment(mainFragment) //파라미터로 화면 전환
    }

    override fun onLoginButtonClick() {
        changeFragment(loginFragment)
    }

    override fun onRegisterButtonClick() {
        changeFragment(registerFragment)
    }

    override fun onRegisterDone() {
        changeFragment(verifyFragment) // 2/25 여기도 verifyFragment와 연결.
    }
}