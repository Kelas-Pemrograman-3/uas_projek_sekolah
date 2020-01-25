<template>
  <q-page>
<div
class="judul text-h6 q-pa-md bg-grey-2 q-mb-md">
        <q-toolbar-title >
          <!-- Mohon Input Data Guru -->
        </q-toolbar-title>
<div class="komentar top-left q-pt-md q-pr-md">
<q-icon name="jadwal" />
  Jadwal
</div>
</div>
    <div class="row justify-center q-pt-md">
      <div class="col-md-5 col-xs-8">
        <q-card>
          <q-card-section>
            <div class="text-h5 q-pb-sm text-center">
              <b>INPUT JADWAL</b>
            </div>
            <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-sm">

              <q-input
                filled
                v-model="kodepelajaran"
                label="kode pelajaran *"
                lazy-rules
                :rules="[ val => val !== null && val !== '' || 'Please type Your NIDN']"
              />

              <q-input
                filled
                v-model="namapelajaran"
                label="nama pelajaran *"
                lazy-rules
                :rules="[ val => val && val.length > 0 || 'Please type Your Password']"
              />

              <q-input
                filled
                v-model="jam"
                label="Jam *"
                lazy-rules
                :rules="[ val => val !== '' && val !== null || 'Please type Your Name']"
              />

              <q-input
                filled
                v-model="hari"
                label="hari *"
                lazy-rules
                :rules="[ val => val !== '' && val !== null || 'Please type Your Name']"
              />

              <q-input
                filled
                v-model="ruangan"
                label="ruangan *"
                lazy-rules
                :rules="[ val => val !== '' && val !== null || 'Please type Your Name']"
              />

              <q-input
                filled
                v-model="nign"
                label="nign *"
                lazy-rules
                :rules="[ val => val !== '' && val !== null || 'Please type Your Name']"
              />

              <q-input
                filled
                v-model="namaguru"
                label="namaguru *"
                lazy-rules
                :rules="[ val => val !== '' && val !== null || 'Please type Your Name']"
              />

              <!-- <q-input
                filled
                v-model="kodemk"
                label="Course Code *"
                lazy-rules
                :rules="[ val => val !== '' && val !== null || 'Please type Your Course Code']"
              />

              <q-input
                filled
                v-model="namamk"
                label="Courses *"
                lazy-rules
                :rules="[ val => val !== '' && val !== null || 'Please type Your Courses']"
              /> -->

              <div>
                <q-btn label="Add +" type="submit" color="light-green-14" class="q-mt-md" />
                <q-btn label="Reset" type="reset" color="indigo-9" flat class="q-ml-sm q-mt-md" />
              </div>
            </q-form>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script>
export default {
  data () {
    return {
      kodepelajaran: '',
      namapelajaran: '',
      jam: '',
      hari: '',
      ruangan: '',
      nign: '',
      namaguru: ''
    }
  },
  methods: {
    onSubmit () {
      this.$axios.post('jadwal/simpan', {
        kodepelajaran: this.kodepelajaran,
        namapelajaran: this.namapelajaran,
        jam: this.jam,
        hari: this.hari,
        ruangan: this.ruangan,
        nign: this.nign,
        namaguru: this.namaguru
      }).then(res => {
        if (res.data.error) {
          this.showNotif(res.data.pesan, 'negative')
        } else {
          this.showNotif(res.data.pesan, 'Positive')
          this.onReset()
        }
      })
    },
    onReset () {
      this.kodepelajaran = ''
      this.namapelajaran = ''
      this.jam = ''
      this.hari = ''
      this.jeniskelamin = ''
      this.ruangan = ''
      this.nign = ''
      this.namaguru = ''
    },
    showNotif (msg, color) {
      this.$q.notify({
        message: msg,
        color: color
      })
    }
  }
}
</script>
