const splash = document.querySelector('.splash');

document.addEventListener('DOMContentLoaded', (e)=>{
    if(!splash){
        console.log("splash element not found")
    }
    setTimeout(()=> {
        splash.classList.add('display-none');
    }, 2000);
})