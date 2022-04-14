package ar.com.wolox.android.cookbook.recipepicker

/**
 * Every instance of [Recipe] will be one of the options the users of the Cookbook will be able
 * to select. To add a [Recipe], just create a new instance under this enum.
 */
enum class Recipe(val fullName: String) {
    LOTTIE("Lottie"),
    MERCADOPAGO("Mercado Pago"),
    ANALYTICS("Firebase Analytics"),
    COROUTINES("Coroutines"),
    GOOGLE_LOGIN("Google login"),
    FACEBOOK_LOGIN("Facebook login"),
    TWITTER_LOGIN("Twitter login"),
    INSTAGRAM_LOGIN("Instagram login"),
    ROOM("RoomDB"),
    MP_CHART("MP Charts"),
    NAVIGATION("Navigation architecture component"),
    DATA_SYNC("Data Sync Recipe with a Pokemon flavor"),
    TESTS("Tests"),
    KOIN("Koin"),
    NOTIFICATIONS("Push Notifications"),
    GRAPH_QL("GraphQL"),
    BIOMETRIC_LOGIN("Login with fingerprint"),
    MAP("Google Maps"),
    ANIMATED_INPUT("Animated Input"),
    BOUNCE_EFFECT("Bounce Effect")
}
