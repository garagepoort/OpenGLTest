
uniform sampler2D texture;
void main()
{
     if( any( lessThan( gl_TexCoord[0].st, vec2( 0.0, 0.0 ) ) ) || 
            any( greaterThan( gl_TexCoord[0].st, vec2( 1.0, 1.0 ) ) ) )
     {
         gl_FragColor = vec4( 1.0, 1.0, 1.0, 1.0 );
         //discard;
     }
     else
     {
            gl_FragColor = texture2D( texture, gl_TexCoord[0].st );
     }
}
