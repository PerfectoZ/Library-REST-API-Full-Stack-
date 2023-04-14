export const oktaConfig = {
    clientId: '0oa948rswp2p01h9L5d7',
    issuer: 'dev-14988774.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    disableHttpsCheck: true
}