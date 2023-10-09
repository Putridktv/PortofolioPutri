package id.utdi.putridwioktaviani


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.utdi.putridwioktaviani.ui.theme.PortofolioPutriTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortofolioPutriTheme() {
                PortofolioApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortofolioApp() {

    var result by remember { mutableStateOf(1) } //variabel untuk menyimpan data id ketika mengklik button

    var squeezeCount by remember { mutableStateOf(0) } //variabel untuk indeks dalam penampilan data

    Scaffold(
        topBar = {  //digunakan untuk membuat bagian header dengan teks Portofolio
            CenterAlignedTopAppBar(
                title = {
                    Text( //bagian untuk membuat teks
                        text = "Portofolio",
                        fontWeight = FontWeight.Bold
                    )
                }, //bagian modifier untuuk mengatur tampilan top bar
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when (result) { //fungsi pilihan data yang akan ditampilkan yang akan disimpan dalam variabel result
                1 -> { //indeks yang digunakan oleh variabel result untuk mengammbil data
                    TextAndImage( //memanggil fungsi TextAndImage untuk menampilkan data
                        textLabelResourceId = R.string.porto_1_title, //menampilkan teks judul porto dengan memanggil string porto_1_title dalam file sfrings.xml
                        textContent = R.string.porto_1_desc, //menampilkan teks deskripsi konten porto dengan memanggil string porto_1_desc dalam file sfrings.xml
                        drawableResourceId = R.drawable.porto_1, //menampilkan gambar porto_1 dalam drawable
                        contentDescriptionResourceId = R.string.porto_1_content_description, //menampilkan deskripsi konten, teks akan tampil ketika terjadi error dalam load data
                        onNextClick = { //funggsi logika untuk button Next "->" ketika di klik
                           result = 2 //untuk indeks next dari 1 ke 2
                            squeezeCount = (2..4).random()
                        },
                        onPreviousClick = { //funggsi logika untuk button Previous "<-" ketika di klik
                            result = 4 //untuk indeks previous dari 1 ke 4
                            squeezeCount = (2..4).random()
                        }
                    )
                }
                2 -> {
                    TextAndImage(
                        textLabelResourceId = R.string.porto_2_title,
                        textContent = R.string.porto_2_desc,
                        drawableResourceId = R.drawable.porto_2,
                        contentDescriptionResourceId = R.string.porto_2_content_description,
                        onNextClick = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                result = 3 //untuk indeks next dari 2 ke 3
                            }
                        },
                        onPreviousClick = {
                            result = 1 //untuk indeks pervious dari 2 ke 1
                            squeezeCount = (2..4).random()
                        }
                    )
                }

                3 -> {
                    TextAndImage(
                        textLabelResourceId = R.string.porto_3_title,
                        textContent = R.string.porto_3_desc,
                        drawableResourceId = R.drawable.porto_3,
                        contentDescriptionResourceId = R.string.porto_3_content_description,
                        onNextClick = {
                            result = 4 //untuk indeks next dari 3 ke 4
                        },
                        onPreviousClick = {
                            result = 2 //untuk indeks previous dari 3 ke 2
                            squeezeCount = (2..4).random()
                        }
                    )
                }
                4 -> {
                    TextAndImage(
                        textLabelResourceId = R.string.porto_4_title,
                        textContent = R.string.porto_4_desc,
                        drawableResourceId = R.drawable.porto_4,
                        contentDescriptionResourceId = R.string.porto_4_content_description,
                        onNextClick = {
                            result = 1 //untuk indeks next dari 4 ke kembali ke 1
                        },
                        onPreviousClick = {
                            result = 3 //untuk indeks previous dari 4 ke 3
                            squeezeCount = (2..4).random()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TextAndImage(  //fungsi TextAndImage menggunakan parameter sesuai dengan data yang digunakan dan modifier sesuai dengan fungsi PortofolioApp agar dapat menampilkan data
    textLabelResourceId: Int,
    textContent: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box( //menggunakan box agar mudah dalam mengatur tata letak
        modifier = modifier
    ) {
        Column( //menggunakan agar tampilan dapat urut kebawah
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image( //digunakan untuk menampilkan gambar dengan data diambil dari drawableResourceId dan contentDescriptionResourceId dalam fungsi PortofolioApp
                painter = painterResource(drawableResourceId),
                contentDescription = stringResource(contentDescriptionResourceId)
            )

            Spacer(modifier = Modifier.padding(10.dp)) //digunakan untuk memberi jarak antara gambar dengan judul

            Text( //digunakan untuk menampilkan teks judul
                text = stringResource(textLabelResourceId), //memanggil teks judul textLabelResourceId dalam file strings.xml
                style = MaterialTheme.typography.headlineSmall, //menggunakan modifier headline dalam mengartur typography
                fontWeight = FontWeight.Bold //untuk membuat teks bold
            )

            Text( //digunakan utnuk menampilkan teks deskripsi portofolio
                text = stringResource(textContent), //memanggil teks judul textContent dalam file strings.xml
                style = MaterialTheme.typography.bodyLarge, //menggunakan modifier body dalam mengartur typography
                textAlign = TextAlign.Justify, //untuk membuat teks rata kanan kiri
                modifier = Modifier
                    .padding(15.dp) //menambahkan padding dengan modifier dalam teks deskripsi portofolio
            )

            Spacer(modifier = Modifier.padding(70.dp)) //untuk memberikan jarak antara teks dengan button

            Row() {//menggunakan row agar button dapat sejajar
                Button( //fungsi untuk membuat button
                    onClick = onPreviousClick, //menambahkan modifier onClick agar button dapat di klik dan menjalankan fungsi onPerviousClick
                    modifier = Modifier.padding(end = 230.dp) // untuk memberikan jarak antara button  "previous" dan "next"
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null) //menggunakn icon arah panah untuk  button previous
                }
                Button(onClick = onNextClick) {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null) //menggunakn icon arah panah untuk  button next
                }
            }

        }
    }
}

@Preview //fungsi preview untuk menampilkan hasil tampilkan tanpa menjalankan emulator
@Composable
fun PortofolioAppPreview() { //fungsi untuk preview
    PortofolioPutriTheme() {
        PortofolioApp() //memanggil fungsi PortofolioApp agar bisa tampil dalam preview
    }
}
