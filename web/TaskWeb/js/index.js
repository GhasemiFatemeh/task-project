//Close Modals with esc
window.addEventListener('keydown', function (event) {
    if (event.key === 'Escape') {
        document.getElementById('login-modal').style.display = 'none';
    }
})
