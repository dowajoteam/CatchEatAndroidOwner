package catcheat.android.owner.ui.common

enum class IntroScreens() {
    OnBoarding1,         // 온보딩1
    OnBoarding2,         // 온보딩2
    SignIn(),           // 로그인
    TOS(),              // 이용 약관
    SignUp(),           // 회원가입
    SignUpCompleted()   // 마무리
}

enum class MainScreens(val displayName: String) {
    Home("Home"),
    Ranking("Ranking");

    companion object {
        val entries = listOf(Home, Ranking)
    }
}

enum class AccessibilityScreens() {
    AccessibilityUpload(),  // 접근성 업로드
    PhysicalRequired(),     // 물리적 접근성 필수 항목
    PhysicalOther(),        // 물리적 접근성 기타 항목
    ServiceRequired(),      // 서비스 접근성 필수 항목
    ServiceOther(),         // 서비스 접근성 기타 항목
    VisualRequired(),       // 시각장애인 지원 필수 항목
    VisualOther(),          // 시각장애인 지원 기타 항목
}

enum class RankingScreens() {
    total(),            // 종합
    physical(),         // 물리적
    service(),          // 서비스
    visual()            // 시각장애인 지원
}

enum class RankingDetailsScreens() {
    RankingDetails()    // 랭킹 상세
}





