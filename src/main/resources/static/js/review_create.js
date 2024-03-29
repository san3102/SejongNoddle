tinymce.init({
  selector: '#myTextarea',
  height: 500,
  theme: 'modern',
  plugins: 'print preview fullpage powerpaste searchreplace autolink directionality advcode visualblocks visualchars fullscreen image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists textcolor wordcount tinymcespellchecker a11ychecker imagetools mediaembed  linkchecker contextmenu colorpicker textpattern help',
  toolbar1: 'formatselect | bold italic strikethrough forecolor backcolor | link | alignleft aligncenter alignright alignjustify  | numlist bullist outdent indent  | removeformat',
  image_advtab: true,
  templates: [
    { title: 'Test template 1', content: 'Test 1' },
    { title: 'Test template 2', content: 'Test 2' }
  ],
  content_css: [
    '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
    '//www.tinymce.com/css/codepen.min.css'
  ]
 });

     const contentForm = document.getElementById('contentForm');
     contentForm.addEventListener('submit', function (event) {
         event.preventDefault(); // 폼 제출 기본 동작 막기

         const content = tinymce.get('myTextarea').getContent();
         const title = document.getElementById('titleInput').value;
         document.getElementById('contentInput').value = content;

         contentForm.submit(); // 폼 제출
     });